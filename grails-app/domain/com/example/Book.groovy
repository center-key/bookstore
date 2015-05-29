package com.example

import org.bson.types.ObjectId

class Book {

   ObjectId id
   String   title
   String   author

   static mapWith = "mongo"

}
