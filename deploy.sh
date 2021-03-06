#!bin/sh
#ip=123.57.37.50
ip=120.27.227.32
local=D:/Biye/frontOfBinwang-master
#local=/Users/owen/Documents/binwang158/frontOfBinwang
remote=/root/frontOfBinwang
remote_sh=/root/sh/binwang-front-sh
appName=frontOfBinwang.jar
##ip=123.57.37.50
#ip=120.27.227.32
#local=/Users/owen/Documents/binwang158/frontOfBinwang
#remote=/root/frontOfBinwang
#remote_sh=/root/sh/binwang-front-sh
#appName=frontOfBinwang.jar
#
#cd ~
#source .bash_profile
#cd ${local}
#netstat -nltp |grep
#打包
#mvn clean package -Dmaven.test.skip=true

ssh root@${ip} <<EOF
sh ${remote_sh}/kill.sh
rm -rf ${remote}
mkdir -p ${remote}
EOF
echo "done for delete original folder"

scp -r ${local}/target/${appName} root@${ip}:${remote}/
scp ${local}/src/main/resources/application.properties root@${ip}:${remote}/
scp ${local}/src/main/resources/application-online.properties root@${ip}:${remote}/
echo "done for send"


ssh root@${ip} <<EOF
sh ${remote_sh}/start.sh
echo "done for restart"
EOF

