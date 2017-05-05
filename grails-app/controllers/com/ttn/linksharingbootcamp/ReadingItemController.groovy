package com.ttn.linksharingbootcamp

import utils.Employee

class ReadingItemController {

    def index() {

        render "hello"
    }

    def change(Long id, Boolean isRead) {

        User user = session.user
        Resource resource = Resource?.get(id)

        log.warn("user-> ${user} id-> ${resource} isread->${isRead}")

        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource=:resource" +
                " and user=:user", [isRead: isRead, resource: resource, user: user])) {

           render flash.message = "Reading Item Status Changed"

        } else {
            render flash.error = "Reading Item Status not Changed"

        }
    }

//    def executeUpdate={
//
//        ReadingItem readingItem = ReadingItem.get(21)
//        Resource resource = Resource?.get(10)
//
//        User user = session.user
//   //     readingItem.user=user
//        Boolean isRead=readingItem.isRead
//        Long id=readingItem.resourceId
//
//        log.warn(" ${isRead} ${resource}")
//
//        ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource.id=:id", [isRead: false, id: 1.toLong()])
//
//        log.warn("${user} ${isRead} ${id}")
//        readingItem.refresh()
//
//        render "isread before ${isRead} -: after updation ${readingItem.isRead}"
//    }
}
