package de.me.edgelord.sjgl.factory;

import de.me.edgelord.sjgl.resource.Resource;

public class Factory {

    private Resource resource;

    public Factory(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {

        return resource;
    }
}
