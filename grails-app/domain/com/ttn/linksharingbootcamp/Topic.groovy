package com.ttn.linksharingbootcamp

import enums.Visibility;

class Topic {

    String topicName;
    User createdBy;
    Date dateCreated;
    Date lastUpdated;
    Visibility visibility

    static belongsTo = [User]

    static hasMany = [subscriptions: Subscription,
                      resources    : Resource]


    static constraints = {

        topicName(unique: ['createdBy'], blank: false)
    }

    String toString() {
        return "${topicName}, ${createdBy}"
    }
/*
    def afterInsert() {

        Topic.withNewSession {

            Subscription subscription = new Subscription(
                    user: this.createdBy,
                    topic: this,
                    seriousness: Constants.SERIOUSNESS)

            if (subscription.validate() && subscription.save(flush: true)) {
                log.info "${subscription}-> ${this.createdBy} subscribed for ${this}"

            } else {
                log.error "Subscription does not occured--- ${subscription.errors.allErrors}"
            }
        }
    }

*/
}
