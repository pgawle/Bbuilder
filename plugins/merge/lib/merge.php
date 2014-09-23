<?php
$url = "https://svn.bushidoit.com/svn/";

$url = $url.$_SERVER['argv'][1];
echo "PATH::::";
echo $url;
echo "\n\n";
echo "UPDATING REPOSITORY\n";
    `svn up`;
    $lines = `svn status | grep "^[CM]" | wc -l`;
    if(trim($lines) > 0){
      die("UPDATE ERROR");
    }
    
echo "TEST MERGE (--dry-run) WITH {$url}\n";
    // --dry-run
    $lines = `svn merge --dry-run $url .  | grep "^[CM]" | wc -l`;
    if(trim($lines) > 0){
      die("MERGE ERROR");
    }

echo "MERGE WITH {$url}\n";
    $merge = `svn merge $url .`;
    $lines = `echo "$merge" | grep "^[CM]" | wc -l`;
    if(trim($lines) > 0){
      die("MERGE ERROR");
    }

    if(preg_match("#Merging (r\d+) through (r\d+) into#", $merge, $revisions)){
      $info = "Merged revision(s) {$revisions[1]}-{$revisions[2]} from devel:";
    }
    else{
      preg_match("#(Recording mergeinfo for merge of r\d+)#", $merge, $revisions);
      $info = $revisions[1];
    }

echo "COMMIT\n";
    `svn ci -m "{$info}"`;

echo "FINISHED WITH INFO - {$info}\n";
?>