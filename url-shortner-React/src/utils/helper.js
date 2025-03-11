import { subDomainList } from "./constant";

export const getApps = () => {
    const subdomain = getSubDomain(window.location.hostname);

    const mainApp = subDomainList.find((app) => app.main);
    if (subdomain === "localhost") return mainApp.app;

    const apps = subDomainList.find((app) => subdomain === app.subdomain);

    return apps ? apps.app : mainApp.app;
}

export const getSubDomain = (location) => {
    const locationParts = location.split(".");    
    return locationParts[0]
};