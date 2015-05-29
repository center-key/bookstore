package org.centerkey.grails.security

import org.bson.types.ObjectId

class User {

   ObjectId id
   Date     created = new Date()
   String   email
   String   hash
   Boolean  enabled = true

   static mapWith = "mongo"
   static mapping = {
      created index: true
      email   index: true
      enabled index: true
      }
   static constraints = {
      email nullable: false, blank: false, unique: true
      }

}
