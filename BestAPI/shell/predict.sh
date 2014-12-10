#!/bin/bash

cd /home/xiaohao/workspace/java_project/SimUser
jar -cvf usim.jar -C bin/ .
scp usim.jar g308@g308pc1:/home/g308/xiaohao/jar

cd /home/xiaohao/workspace/java_project/SimItem
jar -cvf isim.jar -C bin/ .
scp isim.jar g308@g308pc1:/home/g308/xiaohao/jar

cd /home/xiaohao/workspace/java_project/UserBased
jar -cvf ubase.jar -C bin/ .
scp ubase.jar g308@g308pc1:/home/g308/xiaohao/jar

cd /home/xiaohao/workspace/java_project/ItemBased
jar -cvf ibase.jar -C bin/ .
scp ibase.jar g308@g308pc1:/home/g308/xiaohao/jar

cd /home/xiaohao/github/BestAPI/BestAPI/data
scp mapreduce.in g308@g308pc1:/home/g308/xiaohao/data

ssh g308@g308pc1 "/bin/bash /home/g308/xiaohao/bin/mapreduce.sh"
#cat mapreduce.in

scp g308@g308pc1:/home/g308/xiaohao/data/mapreduce.out /home/xiaohao/github/BestAPI/BestAPI/data
