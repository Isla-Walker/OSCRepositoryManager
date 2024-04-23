package org.oscwii.repositorymanager.controllers.api.v3;

import org.oscwii.repositorymanager.controllers.RepoManController;
import org.oscwii.repositorymanager.database.dao.SettingsDAO;
import org.oscwii.repositorymanager.model.RepositoryInfo;
import org.oscwii.repositorymanager.model.api.PublishedApp;
import org.oscwii.repositorymanager.model.app.InstalledApp;
import org.oscwii.repositorymanager.services.FeaturedAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v3", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
public class RepositoryController extends RepoManController
{
    private final FeaturedAppService featuredApp;
    private final SettingsDAO settingsDao;

    @Autowired
    public RepositoryController(FeaturedAppService featuredApp, SettingsDAO settingsDao)
    {
        this.featuredApp = featuredApp;
        this.settingsDao = settingsDao;
    }

    @GetMapping("/information")
    public ResponseEntity<Map<String, Object>> getInformation()
    {
        Optional<String> gitUrl = settingsDao.getSetting("git_url");
        RepositoryInfo info = index.getInfo();

        Map<String, Object> information = Map.of(
                "name", info.name(),
                "provider", info.provider(),
                "description", info.description(),
                "available_categories", index.getCategories(),
                "available_apps_count", index.getContents().size(),
                "git_url", gitUrl.orElse("Unknown")
        );

        return ResponseEntity.ok(information);
    }

    @GetMapping("/contents")
    public ResponseEntity<List<PublishedApp>> getContents()
    {
        List<PublishedApp> apps = new ArrayList<>(index.getContents().size());
        for(InstalledApp installedApp : index.getContents())
            apps.add(new PublishedApp(installedApp));

        return ResponseEntity.ok(apps);
    }

    @GetMapping("/featured-app")
    public ResponseEntity<PublishedApp> getFeaturedApp()
    {
        InstalledApp app = featuredApp.getFeatured();
        if(app == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new PublishedApp(app));
    }
}
