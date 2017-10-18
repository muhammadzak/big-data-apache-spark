// Databricks notebook source
val rdd_raw = sc.textFile("/mnt/learningspark1/adult.data.txt")

rdd_raw.count()
 
val rdd = rdd_raw.filter(x => !x.isEmpty)

 
rdd.count()
 
rdd.take(10)
 
val tokens = rdd.map(r => r.split(","))
 
tokens.take(10)
 
def my_split(line:String) : Array[String] = {
    val words = line.split(",")
    words
}
 
val tokens_with_method = rdd.map(r => r.split(","))
 
tokens_with_method.take(10)
 
case class Adult(
age: String,
workclass: String,
fnlwgt: String,
education: String,
education_num: String,
marital_status: String,
occupation: String,
relationship: String,
race:String,
sex:String,
capital_gain: String,
capital_loss: String,
hours_per_week: String,
native_country: String,
label:String)
 
val adultRDD = tokens_with_method.map(a =>Adult(a(0),a(1),a(2),a(3),a(4),a(5),a(6),a(7),a(8),a(9),a(10),a(11),a(12),a(13),a(14)))
 
adultRDD.collect()
 
rdd.count()
 
val workclass= adultRDD.map(x=>x.workclass)
 
workclass.take(2)
 
workclass.distinct().collect()
 
workclass.distinct().count()
 
val private_emp = adultRDD.filter(_.workclass contains "Private")

 
private_emp.collect()
 
private_emp.count()
 
val workClassEducation= adultRDD.map(x=>(x.workclass,x.education))
 
workClassEducation.take(2)

 
workclass.distinct().collect()

 
workClassEducation.collectAsMap()

 
val marital_status = adultRDD.groupBy(x => x.marital_status)
 
marital_status.collect()

 
val marital_status_count = marital_status.map { case (marital_status, items) => (marital_status, items.size) }.collect()

 
for((k,v) <- marital_status_count){
    println(k+": "+v)
    
}
 
val empgtr50k = adultRDD.filter(x =>x.label == " >50K")
 
empgtr50k.count()
 
adultRDD.count()
 

