<?php
  if(false === array_key_exists('argv', $_SERVER)){
    die('run this in console');
  }
  if(false === array_key_exists('1', $_SERVER['argv'])){
    die('must be 1 parametr (path to csv file)');
  }
  
  $fileName = $_SERVER['argv'][1];
  $output = (true === array_key_exists('2', $_SERVER['argv'])) ? $_SERVER['argv'][2] : '';
  
  $row = 1;
  $langArr = array();
  
  /*
  $langArr will be:
  
  array(
    '1' => array(
      'name' => 'pl-pl',
      'data' => array(
        'play' => 'Graj',
        ...
      )
    ),
    '2' => array(
      'name' => 'en-gb',
      'data' => array(
        'play' => 'Play',
        ...
      )
    ),
    ...
  )
  */
  
  $handle = fopen($fileName, 'r');
  while(($data = fgetcsv($handle)) !== false){
    if(1 == $row){
      for($i = 1, $cnt = count($data); $i < $cnt; $i++){
        $langArr[$i] = array(
          'name' => $data[$i],
          'data' => array()
        );
      }
    }
    else{
      for($i = 1, $cnt = count($data); $i < $cnt; $i++){
        $langArr[$i]['data'][$data[0]] = $data[$i];
      }
    }
    
    ++$row;
  }

  foreach($langArr as $k => $v){
    file_put_contents(
      $output.$v['name'].'.js', 
      "window.BIT = window.BIT || {}; \nBIT.Language = ".json_encode($v['data']).';'
    );
  }
?>