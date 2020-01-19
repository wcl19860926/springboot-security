package com.study.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T>   implements Serializable {

    private  String Msg;
    private  Boolean isSuccess  ;
    private  String code;
    private  T   data;

   public  Result( String  msg ,  boolean  isSuccess){
        this.Msg  =msg;
        this.isSuccess  =isSuccess;

    }


    public  Result(){

    }

}
