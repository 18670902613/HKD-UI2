<template>
    <div class="layout-copy">           
        <div class="links">
            <ul>
                <li>
                    <router-link to="/home">综合服务</router-link>
                </li>
                <li>
                    <a href="http://xxgk.hnust.edu.cn">信息公开 </a>
                </li>
                <li>
                    <a href="http://ygfw.hnust.edu.cn">规章制度 </a>
                </li>
                <li>
                    <a href="http://ygfw.hnust.edu.cn/zczx.jspx">政策咨询</a>
                </li>
                <li>
                    <a href="http://ygfw.hnust.edu.cn/tssl.jspx">投诉受理</a>
                </li>
                <li>
                    <a href="http://ygfw.hnust.edu.cn/yqhy.jspx">舆情回应</a>
                </li>
            </ul>
        </div>
        <div class="school-info">
            <ul>
                <li>
                    <Icon type="ios-telephone-outline"></Icon>
                    电话：0731-58290011
                </li>
                <li>
                    <Icon type="ios-location-outline"></Icon>
                    地址：湖南·湘潭市桃园路(411201)
                </li>
                <li>
                    <Icon type="ios-email-outline"></Icon>
                    邮箱：xcb@hnust.edu.cn
                </li>
            </ul>
        </div>
        <div class="copy-right">
            <p>Copyright©2011 湖南科技大学 湘ICP备05003890号</p>
        </div>           
        </div>
</template>
<script>
    export default {
        
    }
</script>
<style scoped lang="less">
    .layout-copy {
        padding: 20px 0;
        background-color: #8a8d93;
        .links {
            ul {
                width: 456px;
                margin: 0 auto;
                overflow: hidden;
                li {
                    float: left;
                    margin-right: 20px;
                    a {
                        font-size: 14px;
                        color: #fff;
                    }
                }
            }
        }
        .school-info {
            margin-top: 15px;
            ul {
                width: 622px;
                margin: 0 auto;
                overflow: hidden;
                li {
                    float: left;
                    margin-right: 50px;
                    padding-bottom: 6px;
                    font-size: 12px;
                    color: #d0d0d0;
                }
            }
        }
        .copy-right {
            p {
                margin: 0;
                font-size: 12px;
                text-align: center;
                color: #bbb;
            }
        }
    }
</style>