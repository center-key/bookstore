// Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

import org.bson.types.ObjectId

class User {

   ObjectId id
   Date     dateCreated
   String   email
   String   hash
   Boolean  enabled = true

   static mapWith = "mongo"
   static mapping = {
      dateCreated index: true
      email       index: true
      enabled     index: true
      }
   static constraints = {
      email nullable: false, blank: false, unique: true
      }

}
