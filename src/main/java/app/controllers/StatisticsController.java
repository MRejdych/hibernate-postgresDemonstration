package app.controllers;

import app.springconfig.ApplicationConfiguration;
import app.stats.StatisticsAggregator;
import app.stats.StatisticsGenerator;
import app.stats.StatisticsKeeper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class StatisticsController {
    private ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @GetMapping("/statistics")
    public String generateAndShowStatistics(Model model) throws IOException, InterruptedException {

        StatisticsGenerator statisticsGenerator = context.getBean(StatisticsGenerator.class);
        StatisticsKeeper.clearStatistics();
        statisticsGenerator.generate();
        StatisticsAggregator stats = new StatisticsAggregator(StatisticsKeeper.getStatisticsMap());//context.getBean(StatisticsAggregator.class);

        model.addAttribute("createCount", stats.totalCreateQueriesExecuted);
        model.addAttribute("readCount", stats.totalReadQueriesExecuted);
        model.addAttribute("updateCount", stats.totalUpdateQueriesExecuted);
        model.addAttribute("deleteCount", stats.totalDeleteQueriesExecuted);
        model.addAttribute("nativeCreateCount", stats.totalNativeCreateQueriesExecuted);
        model.addAttribute("nativeReadCount", stats.totalNativeReadQueriesExecuted);
        model.addAttribute("nativeUpdateCount", stats.totalNativeUpdateQueriesExecuted);
        model.addAttribute("nativeDeleteCount", stats.totalNativeDeleteQueriesExecuted);

        model.addAttribute("createAvgTime", stats.avgCreateQueriesTime);
        model.addAttribute("readAvgTime", stats.avgReadQueriesTime);
        model.addAttribute("updateAvgTime", stats.avgUpdateQueriesTime);
        model.addAttribute("deleteAvgTime", stats.avgDeleteQueriesTime);
        model.addAttribute("nativeCreateAvgTime", stats.avgNativeCreateQueriesTime);
        model.addAttribute("nativeReadAvgTime", stats.avgNativeReadQueriesTime);
        model.addAttribute("nativeUpdateAvgTime", stats.avgNativeUpdateQueriesTime);
        model.addAttribute("nativeDeleteAvgTime", stats.avgNativeDeleteQueriesTime);

        return "showStatistics";
    }
}
