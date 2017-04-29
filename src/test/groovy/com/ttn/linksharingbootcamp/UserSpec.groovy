package com.ttn.linksharingbootcamp

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    void "test one"(){
        expect:
        true
    }

    @Unroll("Executing #sno")
    void "testUserValidations"() {
        setup:
        User user = new User(email: email, userName: userName, password: password, firstName: fname, lastName: lname, photo: photo,
                    admin: admin, active: active)
        when:
        Boolean result = user.validate()

        then:
        result == valid
        where:
        sno | email               | userName    | password | fname     | lname     | photo | admin  | active  | valid
        1   | "sargam123"         |  "sargam"   | ""       | null      | ""        | null  | true   | false   | false
        1   | "sar@gmail.com"     |  "sargam12" | "1234"   | ""        | "kumar"   | null  | null   | null    | false
        1   | "sargam4@gmail.com" |  "abcd"     | "098765" | "sargam"  | "sachdeva"| null  | true   | true    | true
    }


    def "EmailAddressAndUserNameOfUserShouldBeUnique"() {
        setup:
        String email = "sargam@tothenew.com"
        String userName = "sargam12"
        String password = 'password'
        User user = new User(email:email, userName: userName, password: password, firstName: "Sargam", lastName: "Sachdeva", photo: null,
                    admin: true, active: true )

        when:
        user.save()

        then:
        user.count() == 1

        when:
        User newUser = new User(email:email, userName: userName, password: "123456", firstName: "Sar", lastName: "Kumar", photo: null,
                admin: true, active: false)
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 2
        newUser.errors.getFieldErrorCount('email') == 1
        newUser.errors.getFieldErrorCount('userName') == 1
    }

    def "getUserFullName"() {
        expect:
        new User(firstName: firstName, lastName: lastName).fullName == name

        where:
        firstName | lastName | name
        "puneet"  | "kaur"   | "puneet kaur"
        ""        | "kaur"   | "kaur"
        null      | "kaur"   | "kaur"
        "neha"    | ""       | "neha"
        "neha"    | null     | "neha"
        null      | null     | ""
    }

    def "tostringCheck"() {

        given:
        User user = new User(userName: userName)

        when:
        result == user.toString()

        then:
        noExceptionThrown()

        where:
        userName          | result
        "sargamsachdeva" | "sargamsachdeva"
    }


}
