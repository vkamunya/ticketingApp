package com.example.sgrticket.models


class Ticket {

    var name: String? = ""
    var source: String? = ""
    var destination: String? = ""
    var ticketnumber: String? = ""


    constructor(name: String, source:String, destination: String, ticketnumber :String,){
        this.name = name
        this.source = source
        this.destination = source
        this.ticketnumber = source

    }


}