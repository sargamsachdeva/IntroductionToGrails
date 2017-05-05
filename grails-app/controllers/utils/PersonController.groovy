package utils

import co.PersonCO

class PersonController {

    def index() {

        render (view:'index1')
    }

    def bindingUsingParams() {

        Person person = new Person(params)
        render person
    }

    def bindingUsingProperties() {

        Person person = new Person()
        person.properties = params
        render person
    }

    def bindData() {

        Person person = new Person()
        bindData(person,params,['age'])
        render person
    }

    def bindingCO(PersonCO personCO) {

        render personCO
    }

    def upload() {

        def f = params.myFile
        render f.inputStream.text
    }

    def download()
    {
        byte[] bytes = new File("/home/sargam/imp.txt").bytes

        response.setHeader("Content-disposition","attachment; filename=" + "demofile")

        response.setContentType("text/plain")

        response.outputStream << bytes


    }

}
