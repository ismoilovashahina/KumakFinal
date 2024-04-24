package com.example.projectworkaprilkumak.datas

class MyFundraisingData{
    var pushKey:String? = null
    var id:Int? = null
    var title:String? = null
    var imgId:Int? = null
    var raised:Int? = null
    var toRaise:Int? = null
    var donN:Int? = null
    var daysLeft:Int? = null
    var category:String? = null
    var nameOfRecipients:String? = null
    var donationDocuments:String?  =null
    var medicalDocuments:String? = null
    var story:String? = null
    var imageLink:String? = null
    constructor()
    constructor(id:Int?, title:String?, imgId:Int?, raised:Int?, toRaise:Int?, donN:Int?, daysLeft:Int?, category:String?, nameOfRecipients:String?, donationDocuments:String?, medicalDocuments:String?, story:String?){
        this.id = id
        this.title = title
        this.imgId = imgId
        this.raised = raised
        this.toRaise=  toRaise
        this.donN = donN
        this.daysLeft = daysLeft
        this.category = category
        this.nameOfRecipients = nameOfRecipients
        this.donationDocuments = donationDocuments
        this.medicalDocuments = medicalDocuments
        this.story = story
    }
    constructor(title:String?,
                imgId:Int?,
                raised:Int?,
                toRaise:Int?,
                donN:Int?,
                daysLeft:Int?,
                category:String?,
                nameOfRecipients:String?,
                donationDocuments:String?,
                medicalDocuments:String?,
                story:String?){
        this.title = title
        this.imgId = imgId
        this.raised = raised
        this.toRaise=  toRaise
        this.donN = donN
        this.daysLeft = daysLeft
        this.category = category
        this.nameOfRecipients = nameOfRecipients
        this.donationDocuments = donationDocuments
        this.medicalDocuments = medicalDocuments
        this.story = story
    }

    constructor(
        pushKey: String?,
        id: Int?,
        title: String?,
        imgId: Int?,
        raised: Int?,
        toRaise: Int?,
        donN: Int?,
        daysLeft: Int?,
        category: String?,
        nameOfRecipients: String?,
        donationDocuments: String?,
        medicalDocuments: String?,
        story: String?,
        imageLink:String?
    ) {
        this.pushKey = pushKey
        this.id = id
        this.title = title
        this.imgId = imgId
        this.raised = raised
        this.toRaise = toRaise
        this.donN = donN
        this.daysLeft = daysLeft
        this.category = category
        this.nameOfRecipients = nameOfRecipients
        this.donationDocuments = donationDocuments
        this.medicalDocuments = medicalDocuments
        this.story = story
        this.imageLink = imageLink
    }


}


