package com.ttn.linksharingbootcamp

class User {

    String email;
    String userName;
    String password;
    String firstName;
    String lastName;

    Byte[] photo;
    Boolean admin;
    Boolean active;
    Date dateCreated;
    Date lastUpdated;


    static transients = ['fullName']

    static hasMany = [topics : Topic,
                      subscriptions: Subscription,
                      resources:Resource,
                      readingItems:ReadingItem,
                      resourceRatings:ResourceRating]


    static mapping = {
        photo(sqlType: 'longblob')
    }


    static constraints = {

        email(unique: true, email: true ,blank: false)

        userName(unique: true, blank: false)

        password(minSize: 5, blank: false)

        firstName(blank: false)

        lastName(blank: false)

        //[photo,admin,active](nullable: true)
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)

    }

    String getFullName() {
        [firstName, lastName].findAll { it }.join(' ')

    }

    String toString() {

        return this.userName
    }

}
