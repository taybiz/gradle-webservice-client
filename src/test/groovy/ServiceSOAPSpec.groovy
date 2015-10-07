package com.taybiz.api.integration.test

import wslite.soap.*

class ServiceSOAPSpec extends spock.lang.Specification {
      
  static NAMESPACE = 'http://www.27seconds.com/Holidays/US/Dates/'
  
  def "Return 200 with a clean return"() {
  
    setup:
    def serviceURL = System.getProperty("soapServiceURL")
    println "Aimed at endpoint: $serviceURL"  
    def endpoint = new SOAPClient( serviceURL )
    
    when:
    def resp = endpoint.send(useCaches:false,
                           followRedirects:false) {
      body {
        GetMothersDay(xmlns:NAMESPACE) {
          year(2015)
        }
      }
    }
    
    then:
    with(resp) {
        httpResponse.statusCode == 200
        httpResponse.contentType == "text/xml"
        //XMLSlurper here:
        GetMothersDayResponse.GetMothersDayResult.text() == '2015-05-10T00:00:00'
    }
    
  } //end testcase
  
} //end testspec