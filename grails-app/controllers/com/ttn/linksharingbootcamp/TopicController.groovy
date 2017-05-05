package com.ttn.linksharingbootcamp

import enums.Seriousness
import enums.Visibility

class TopicController {

    def index() { }


    def show(Long id) {

        Topic topic = Topic?.read(id)

        if (topic) {

            if (topic.visibility == Visibility.PUBLIC) {
                render "success public"
            }

            else if (topic.visibility == Visibility.PRIVATE) {

                if (Subscription?.findByUserAndTopic(session.user, topic)) {

                    render "success private"
                }

                else {

                    redirect(controller:'login' , action:'index')
                    log.info("Cannot access private topic")
                }
            }
        }

        else {
            redirect(controller:'login' , action:'index')
            log.info("Topic does not exist!")
        }
    }

    def save(String topicName,String visibility) {

        if(session.user) {


          Topic topic = new Topic(topicName: topicName, createdBy: session.user, visibility: Visibility.convertIntoEnum(visibility))
            topic.createdBy = session.user

            log.info("topic${topic}")

            if(topic.hasErrors()) {

                //flash.error = "topic.checkErrors"
                render([error: 'an error occurred'])
            }

            else {

                topic.save(flush:true)

                if(topic) {

//                    flash.error = "topic.saved"
                    render "success"
                }

                else

                    render "Topic not saved"
            }
        }
    }
}