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
    String confirmPassword
    Date dateCreated;
    Date lastUpdated;


    static transients = ['fullName'
                        ,'confirmPassword']

    static hasMany = [topics : Topic,
                      subscriptions: Subscription,
                      resources:Resource,
                      readingItems:ReadingItem,
                      resourceRatings:ResourceRating]


    static mapping = {

        id(sort: 'desc')
        photo(sqlType: 'longblob')
    }


    static constraints = {

        email(unique: true, email: true ,blank: false)

        userName(unique: true, blank: false)

        password(minSize: 5, blank: false,

            validator: {password, obj ->
                def password2 = obj.confirmPassword
                password2 == password ? true : ['password.mismatch']
            })

        firstName(blank: false)

        lastName(blank: false)

        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)

        confirmPassword(nullable: true, blank: true)
    }

    String getFullName() {
        [firstName, lastName].join(' ')

    }

    String toString() {

        return this.userName
    }

   /*
    List<Resource> unreadResources() {

        return ReadingItem.createCriteria().list {
            projections {
                property('resource')
            }
            eq('user', this)
            eq('isRead', false)
        }
    }
*/
}
