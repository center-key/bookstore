// Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors

package org.centerkey.grails.security

import org.bson.types.ObjectId

class Role {

   ObjectId id
   Date     dateCreated
   String   code
   String   name

   static mapWith = "mongo"
   static mapping = {
      code index: true
      }

}
