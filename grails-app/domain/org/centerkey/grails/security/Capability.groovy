// Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

import org.bson.types.ObjectId

class Capability {

   ObjectId id
   Date     dateCreated
   String   userId
   String   roleId

   static mapWith = "mongo"
   static mapping = {
      dateCreated index: true
      userId      index: true
      roleId      index: true
      }

}
