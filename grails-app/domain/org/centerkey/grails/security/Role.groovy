package org.centerkey.grails.security

import org.bson.types.ObjectId

class Role {

   ObjectId id
   Date     created = new Date()
   String   code
   String   name

   static mapWith = "mongo"
   static mapping = {
      created index: true
      code    index: true
      }

}
