package com.ttn.linksharingbootcamp

import org.springframework.context.MessageSource

class TestController {

    static defaultAction = "test"
    MessageSource messageSource

    static allowedMethods = [index: 'GET', update: ['PUT', 'POST']]

    def index() {
        render "Params ${params}" "session ${session.name}"
        String msg=messageSource.getMessage("default.show.label",['glku','hjh'] as Object[],Locale.JAPANESE)
        render msg;
//2 action implicit chaining, interceptors called twice demo
/*forward(action: "renderText", params: [actionName : actionName,
firstInterceptorRan: params.firstInterceptorRan])*/
    }

    def test() {
        render "${session.name} Action : ${actionName}"
    }

//default action invoked from urlMappings
    def noAction() {
        render "${controllerName}/${actionName}"
    }

//Error uri handling
    def save() {
        response.sendError(404)
    }

//For allowedMethods tests
    def update() {
        render ":Updated"
    }

//For testing "render"
    def firstScope() {
        render view: 'first'
    }

    String renderText() {
        render "Params ${params}"
    }

}


