package com.ttn.linksharingbootcamp

import constants.Constants
import enums.Seriousness

class SubscriptionController {

    def index() { }


    def save(Long topicId) {

        Topic topic = Topic.get(topicId)
        if (topic) {
            User user = session.user
            Subscription subscription = new Subscription(user: user, topic: topic,seriousness: Seriousness.CASUAL)
            if (subscription.save(flush: true)) {
                render flash.message = "Subscription saved successfully"
            } else {
                render flash.error = subscription.errors.allErrors.collect { message(error: it) }.join(", ")
            }
        } else {
            flash.error = "Topic Does not Exist"
        }
       // redirect(controller: 'user', action: 'index')
    }





    def delete(Long topicId) {

        Topic topic = Topic.get(topicId)
        User user = session.user
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)
        if (subscription) {
            subscription.delete(flush: true)
            render flash.message = "Subscription deleted"
        } else {
           render flash.error = "Subscription not found"
        }
      //  redirect(controller: 'user', action: 'index')
    }


    def update(Long topicId, String serious) {

        Topic topic = Topic?.get(topicId)
        User user = session.user
        Subscription subscription = Subscription.findByUserAndTopic(user, topic)

        if (subscription) {
            if (Seriousness.checkSeriousness(serious) != null) {
                subscription.seriousness = Seriousness.checkSeriousness(serious)

                if (subscription.save(flush: true)) {
                    render flash.message = "Subscription saved successfully"

                } else {
                  render flash.error = "Error saving subscription"
                }

            } else {
                render flash.error = "Proper Seriousness string not provided"
            }

        } else {
            render flash.error = "Subscription not found"
        }
     //   redirect(controller: 'user', action: 'index')
    }


}
