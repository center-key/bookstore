package org.centerkey.grails.security

import org.bson.types.ObjectId

class Capability {

   ObjectId id
   Date     created = new Date()
   String   userId
   String   roleId

   static mapWith = "mongo"
   static mapping = {
      created index: true
      userId  index: true
      roleId  index: true
      }

}
