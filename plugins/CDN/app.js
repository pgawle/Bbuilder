/*
 * project and project.getProperty are <script> tag function
 * it is set in project.properties
 * Documentation of script tag:
 * http://ant.apache.org/manual/Tasks/script.html
 * 
 */

var cdnUrl = project.getProperty('cdn.url');

/*now javascript
 * normal Jquery way
 */

var trimStart = function(pString){
  var path = pString;
  if('.'=== path.charAt(0) || '/' === path.charAt(0)){
      return trimStart(path.substring(1));
  }
  
  return pString;
}

var addCDN = function(pString, pCDN){
  if(1 < pString.split('http').length){
    return pString;
  }else{
    return pCDN+"/"+trimStart(pString);    
  }
}


$('link').each(function(){ 
   this.href = addCDN(this.href,cdnUrl);
});


$('script').each(function(){ 
   this.src = addCDN(this.src,cdnUrl);
});

$('img').each(function(){ 
   this.src = addCDN(this.src,cdnUrl);
});