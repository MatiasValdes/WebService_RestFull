/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.modelo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RyC
 */
@XmlRootElement
public class Token implements Serializable{

        String token;

        public Token() {
        }

        public Token(String token) {
            this.token = token;
        }        

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Token{" + "token=" + token + '}';
        }
}
