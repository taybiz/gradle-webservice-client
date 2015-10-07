package com.taybiz.api.integration.test

import wslite.rest.*

class ServiceRESTSpec extends spock.lang.Specification {
    
  def "Return 200 with a clean return"() {
  
    setup:
    def serviceURL = System.getProperty("restServiceURL")
    println "Aimed at endpoint: $serviceURL"
    def endpoint = new RESTClient( serviceURL )
    
    when:
    def resp = endpoint.get( path: '/posts', query : [ userId : 1 ] )
    
    then:
    with(resp) {
        statusCode == 200
        contentType == "application/json"
    }
    with(resp.json) { posts ->
      posts.size() == 10
    }
    
  } //end testcase
  
} //end testspec