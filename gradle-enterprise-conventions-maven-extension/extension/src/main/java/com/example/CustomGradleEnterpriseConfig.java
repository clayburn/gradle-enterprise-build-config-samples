package com.example;

import com.gradle.maven.extension.api.GradleEnterpriseApi;
import com.gradle.maven.extension.api.cache.BuildCacheApi;
import com.gradle.maven.extension.api.scan.BuildScanApi;
import org.apache.maven.execution.MavenSession;

final class CustomGradleEnterpriseConfig {

    void extendBuildScan(GradleEnterpriseApi api, MavenSession session) {
        BuildScanApi buildScan = api.getBuildScan();

        buildScan.value("Parallel enabled", Boolean.toString(session.isParallel()));
        buildScan.value("Module count", Integer.toString(session.getProjects().size()));
        session.getRequest().getActiveProfiles().forEach(profile -> buildScan.value("Active profile", profile));

        BuildCacheApi buildCache = api.getBuildCache();
        buildScan.value("Local cache enabled", Boolean.toString(buildCache.getLocal().isEnabled()));
        buildScan.value("Remote cache enabled", Boolean.toString(buildCache.getRemote().isEnabled()));

        buildScan.value("Convention Extension Version", getClass().getPackage().getImplementationVersion());
    }
}
