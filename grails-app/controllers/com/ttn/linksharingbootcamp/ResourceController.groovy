package com.ttn.linksharingbootcamp

import co.ResourceSearchCO
import enums.Visibility
import org.hibernate.ObjectNotFoundException

class ResourceController {

    def index() { }

    def delete(Long id) {

        Resource resource = Resource.load(id)

        log.info("resource ${resource}")

        if(resource) {

            resource.delete(flush:true)
            render "success"
        }
        else {

          //  throw new Exception("exception")

            render "Resource not found.."
        }
    }

    def search(ResourceSearchCO co) {

        if (co.q) {
            co.visibility = Visibility.PUBLIC
        }

        List<Resource> resources = Resource.search(co).list()
        render "Result -> ${resources}"
    }
}
