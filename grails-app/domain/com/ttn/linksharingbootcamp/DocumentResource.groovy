package com.ttn.linksharingbootcamp

class DocumentResource extends Resource {

    String filePath

    static constraints = {
        filePath(blank: false)
    }

    String toString() {
        return filePath
    }
}
