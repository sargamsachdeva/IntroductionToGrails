package com.ttn.linksharingbootcamp

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated


    static hasMany = [ratings : ResourceRating,
                      readingItems: ReadingItem]


    static mapping = {
        description(type: 'text')
    }

    static belongsTo = [topic: Topic]

    static constraints = {
        description(blank: false)
    }


    String toString() {
        return "${description}"
    }


}
