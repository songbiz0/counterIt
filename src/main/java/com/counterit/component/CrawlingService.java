package com.counterit.component;

import com.counterit.model.ChampEntity;
import com.counterit.model.CrawlingEntity;
import com.counterit.model.StatsEntity;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.CrawlingRepository;
import com.counterit.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Service
public class CrawlingService {

    @Autowired
    ChampRepository champRepository;
    @Autowired
    StatsRepository statsRepository;
    @Autowired
    CrawlingRepository crawlingRepository;

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe"; //드라이버 경로
    public static final List<String> tiers = List.of("silver", "gold_plus");
    public static final List<String> lanes = List.of("middle");

    public void crawling(String version) {
        Optional<CrawlingEntity> checkCrawling = crawlingRepository.findByVer(version);
        if(checkCrawling.isPresent()) {
            return;
        }

        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<ChampEntity> champEntityList = champRepository.findAll();

        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
        options.addArguments("headless");

        //위에서 설정한 옵션은 담은 드라이버 객체 생성
        //옵션을 설정하지 않았을 때에는 생략 가능하다.
        //WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
        WebDriver driver = new ChromeDriver(options);

        CrawlingEntity crawlingEntity = new CrawlingEntity();
        crawlingEntity.setVer(version);
        crawlingEntity.setStat("INSERTING");
        crawlingRepository.save(crawlingEntity);

        for(int i=0; i<tiers.size(); i++) {
            String tierLog = tiers.get(i);
            for(int j=0; j<lanes.size(); j++) {
                String laneLog = lanes.get(j);
                System.out.println("NOW CRAWLING -> " + tierLog + " - " + laneLog + " (" + version + ")");
                for(ChampEntity champEntity : champEntityList) {
                    //이동을 원하는 url
                    String champName = champEntity.getEnnm().toLowerCase().replace("'", "").replace(".", "").replace(" ", "");
                    if(champName.equals("renata glasc")) {
                        champName = "renata";
                    }
                    String laneName = lanes.get(j);
                    String tierName = tiers.get(i);

                    String url = "https://lolalytics.com/lol/" + champName +
                            "/counters/?lane=" + laneName + "&vslane=" + laneName +
                            "&tier=" + tierName + "&patch=" + version;
                    //WebDriver을 해당 url로 이동한다.
                    driver.get(url);

                    //브라우저 이동시 생기는 로드시간을 기다린다.
                    try {Thread.sleep(2000);} catch (InterruptedException e) {}

                    String name = "Counter_name__7IY8G";
                    String delta = "Counter_delta__sJfkF";
                    String games = "Counter_games__9JfXq";

                    List<WebElement> el1 = driver.findElements(By.className(name));
                    List<WebElement> el3 = driver.findElements(By.className(delta));
                    List<WebElement> el4 = driver.findElements(By.className(games));

                    for(int k=0; k<el1.size(); k++) {
                            String delta2 = el3.get(k).getText().split(" ")[4];
                            Double deltaDouble = Double.parseDouble(delta2);
                            String games2 = el4.get(k).getText().split(" ")[0].replace(",", "");
                            Integer gamesInteger = Integer.parseInt(games2);

                            StatsEntity statsEntity = new StatsEntity();
                            statsEntity.setDelta(deltaDouble);
                            statsEntity.setGames(gamesInteger);
                            statsEntity.setLane(laneName);
                            statsEntity.setTier(tierName);
                            statsEntity.setStdChampEntity(champEntity);
                            statsEntity.setVer(version);
                            ChampEntity vsChampEntity = champRepository.findByEnnm(el1.get(k).getText());
                            statsEntity.setVsChampEntity(vsChampEntity);

                            statsRepository.save(statsEntity);
                    }
                }
            }
        }

        crawlingEntity.setStat("COMPLETE");
        crawlingRepository.save(crawlingEntity);

        try {
            if(driver != null) {
                driver.close();
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
