


#!/bin/sh
#
#
APP_DIR=/opt/archimedes-parent/archimedes-payment
APP_NAME=payment-server
APP_CONF=$APP_DIR/src/main/resources/application.properties
APP_OPTS="-server -Xms3g -Xmx3g -Xss256K -Djava.awt.headless=true -Dfile.encoding=utf-8 -XX:+UseParallelGC -XX:PermSize=256m -XX:MaxPermSize=256m"
#set java home
export JAVA_HOME=/usr/local/jdk1.8.0_181


usage() {
    echo "Usage: sh eju-micro-app.sh [start|stop|deploy]"
    exit 1
}

kills(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [[ $tpid ]]; then
        echo 'Kill Process!'
        kill -9 $tpid
    fi
}
start(){
    rm -f $APP_DIR/tpid

    nohup java -jar   $APP_DIR/"$APP_NAME".jar > nohup &


    echo $! > $APP_DIR/tpid
    echo Start Success!
}

stop(){
        tpid1=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    echo tpid1-$tpid1
        if [[ $tpid1 ]]; then
        echo 'Stop Process...'
        kill -15 $tpid1
    fi
    sleep 5
    tpid2=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
        echo tpid2-$tpid2
    if [[ $tpid2 ]]; then
        echo 'Kill Process!'
        kill -9 $tpid2
    else
        echo 'Stop Success!'
    fi

}

check(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [[ tpid ]]; then
        echo 'App is running.'
    else
        echo 'App is NOT running.'
    fi
}

deploy() {
    kills
    svn update
    rm -rf $APP_DIR/target
    mvn clean package
    cp $APP_DIR/target/"$APP_NAME".jar $APP_DIR
}

case "$1" in
    "start")
        start
        ;;
    "stop")
        stop
        ;;
    "kill")
        kills
        ;;
    "deploy")
        deploy
        ;;
    *)
        usage
        ;;
esac

