package com.example.sgrticket.models


class Ticket {

    var name: String? = null
    var source: String? = null
    var destination: String? = null
    var ticketnumber: String? = null
    var recordId: String? = null

    constructor(name: String, source:String, destination: String, ticketnumber :String, recordId: String,){
        this.name = name
        this.source = source
        this.destination = source
        this.ticketnumber = source
        this.recordId = recordId
    }


}