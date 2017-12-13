<template>
    <div class="layout">       
        <header-panel 
            :visitor="visitor" 
            :servicesData="services">
        </header-panel>
        <div class="layout-content">
            <carousel autoplay class="home-carousel" v-if="visitor">
                <carousel-item v-for="carousel in carousels" :key="carousel.id">
                    <div class="demo-carousel">
                        <router-link :to="carousel.router">
                            <img :src="carousel.imgUrl">
                        </router-link>
                    </div>
                </carousel-item>                       
            </carousel>
            <!-- 服务选择和过滤 开始 -->
            <div class="service-filter">
                <div class="filter-header">                    
                    <div class="by-fun" id="filter-by-fun">                    
                        <Tag 
                            v-for="(category, index) in categories.data" 
                            :class="{ active : index == 0 }"  
                            @click.native="tagChangeEvent('filter-by-fun')" 
                            :key="category.id" 
                            :category-key="categories.key" 
                            :category-type="category.type">
                            {{ category.text }}
                        </Tag>                      
                    </div>
                    <div class="by-other" id="filter-by-other">
                        <span class="label">部门：</span>                    
                        <div class="content" :class="{'show-all-dep' : !showAllDep}" id="departments-content">                            
                            <Tag 
                                v-for="(department, index) in departments.data" 
                                :class="{ active : index == 0 }"  
                                @click.native="tagChangeEvent('filter-by-other')" 
                                :key="department.id" 
                                :category-key="departments.key" 
                                :category-type="department.type">
                                {{ department.text }}
                            </Tag>
                        </div>
                        <Tooltip content="更多部门" placement="left">
                            <Icon :type="showAllDep ? 'android-remove' : 'android-add'" 
                                class="show-all-ctrl" 
                                @click.native="showAllDep=!showAllDep">                           
                            </Icon>                                            
                        </Tooltip>
                    </div> 
                    <div class="order-by" id="filter-by-order">  
                        <span class="label">排序：</span>
                        <Tag 
                            v-for="(order, index) in toOrderBy" 
                            @click.native="tagChangeEvent('filter-by-order')" 
                            :order-key="order.key" 
                            :key="order.id">
                            {{ order.text }}
                        </Tag>                                               
                    </div>       
                </div>
                <div class="filter-body" :class="{ 'no-result': noResult }">
                    <div class="no-result-msg" v-if="noResult">很遗憾！没有找到您要的服务...</div>
                    <Card 
                        v-for="serv in CurrPageServices" 
                       :key="serv.id" 
                       class="service-card">
                       <router-link :to="'/service/' + serv.id">
                           <div class="service-icon">
                               <img :src="serv.pIcon">
                           </div>
                           <div class="service-title">
                               {{ serv.pName }}
                           </div> 
                           <div class="service-raty">                           
                               <Rate allow-half :value.sync="serv.pPF" disabled></Rate>
                           </div>                   
                           <p style="font-size:12px;">{{ serv.funText }}</p>
                           <Tooltip class="btn-collection" :content="isCollection(serv.id) ? '已收藏' : '收藏'" placement="bottom">
                               <Icon 
                                   @click.prevent.native="collectionServEvent(serv.id)" 
                                   :type="isCollection(serv.id) ? 'android-favorite' : 'android-favorite-outline'">                               
                               </Icon>
                           </Tooltip>        
                       </router-link>
                   </Card>              
                </div>
                <div class="filter-footer">
                    <transition name="slide-fade">
                        <Page 
                            v-if="servicesFilted.total > 10"   
                            :total="servicesFilted.total" 
                            :page-size="servicesFilted.pageSize"
                            :current="servicesFilted.currPage"
                            @on-change="changePageEvent"  
                            class="home-page">
                        </Page>
                    </transition>
                </div>
            </div>
            <!-- 服务选择和过滤 结束 -->
            <!-- 通知和新闻 开始 -->
            <div class="news">               
                <dl class="home-list" v-for="item in news" :style="item.style" :key="item.id">
                    <dt>{{ item.title }}</dt>
                    <dd v-for="subItem in item.data" :key="subItem.id">
                        <a :href="subItem.noticeUrl">
                            <Icon :type="subItem.icon"></Icon>
                            <span class="title">{{ subItem.title }} </span>
                            <span class="time">{{ subItem.time }}</span>
                        </a>
                    </dd>
                </dl>                
            </div>
            <!-- 通知和新闻 结束 -->
        </div>
        <footer-panel></footer-panel>
    </div>
</template>
<script>
import axios from "axios";
import { Utils } from "../../lib/utils.js";
import headerPanel from "../../components/headerPanel.vue";
import footerPanel from "../../components/footerPanel.vue";
import {
  getUserData,
  getDepartmentsData,
  getNewsData,
  getServicesData,
  getCollectionsData
} from "../../../data/getData.js";

export default {
  components: {
    headerPanel,
    footerPanel
  },
  created() {
    // 请求数据
    axios
      .all([
        this.getUserData(),
        this.getDepartmentsData(),
        this.getNewsData(),
        this.getServicesData(),
        this.getCollectionsData()
      ])
      .then(
        axios.spread((user, departments, news, service, collections) => {
          // 用户信息
          this.user = user;
          // 部门数据
          this.departments.data = departments.data.data;
          // 通知公告和新闻数据
          this.news = news.data.data;
          // 该用户权限下的所有服务数据
          this.services.data = service;
          this.services.total = this.services.data.length;
          // 该用户的收藏列表
          this.collections = [...collections.data.data];
          // 过滤器数据重置
          this.filterReset();
        })
      );
    this.$Message.config({
      top: 50,
      duration: 3
    });
  },
  data() {
    return {
      getUserData: getUserData, // 请求用户数据方法
      getDepartmentsData: getDepartmentsData, // 请求部门数据方法
      getNewsData: getNewsData, //请求通知和新闻数据方法
      getServicesData: getServicesData, // 请求该用户权限下的所有服务数据方法
      getCollectionsData: getCollectionsData, // 请求该用户的收藏列表数据方法
      visitor: true, // 登录状态
      showSearch: false, // 搜索框是否显示
      showAllDep: false, // 部门view是否展开
      user: {},
      // 走马灯banner图配置及跳转链接配置
      carousels: [
        {
          router: "/home",
          imgUrl: "../../src/images/banner1.jpg"
        },
        {
          router: "/home",
          imgUrl: "../../src/images/banner2.jpg"
        },
        {
          router: "/home",
          imgUrl: "../../src/images/banner3.jpg"
        }
      ],
      // 服务分类数据
      categories: {
        key: "funType",
        data: [
          {
            type: "0",
            text: "所有服务"
          },
          {
            type: "1",
            text: "服务指南类"
          },
          {
            type: "2",
            text: "单点登录类"
          },
          {
            type: "3",
            text: "服务预约类"
          },
          {
            type: "4",
            text: "在线办理类"
          },
          {
            type: "5",
            text: "数据查询类"
          }
        ]
      },
      // 部门数据
      departments: {
        key: "department",
        data: []
      },
      // 排序方式配置
      toOrderBy: [
        {
          key: "onLineTime",
          text: "最新"
        },
        {
          key: "hitCount",
          text: "最热"
        },
        {
          key: "raty",
          text: "口碑"
        }
      ],
      // 该用户权限下的所有服务数据
      services: {
        total: 0,
        currPage: 1,
        pageSize: 10,
        data: []
      },
      // 收藏列表
      collections: [],
      // 过滤器数据
      servicesFilted: {
        total: 0,
        currPage: 1,
        pageSize: 10,
        data: []
      },
      // 通知公告和新闻数据
      news: []
    };
  },
  computed: {
    // 过滤器当前页面数据
    CurrPageServices: function() {
      return this.servicesFilted.data.slice(
        (this.servicesFilted.currPage - 1) * this.servicesFilted.pageSize,
        this.servicesFilted.currPage * this.servicesFilted.pageSize
      );
    },
    // 过滤器有无数据
    noResult: function() {
      return this.servicesFilted.total < 1;
    }
  },
  methods: {
    // 过滤器切换分页
    changePageEvent(e) {
      this.servicesFilted.currPage = e;
    },
    // 过滤条件改变
    tagChangeEvent(id) {
      // 过滤器UI交互
      let tags = document.getElementById(id).getElementsByClassName("ivu-tag");
      for (let tag of tags) {
        Utils.domClass.removeClass(tag, "active");
      }
      let ele = Utils.domClass.hasClass(event.target, "ivu-tag")
        ? event.target
        : event.target.parentElement;
      Utils.domClass.addClass(ele, "active");

      // 过滤
      if (id != "filter-by-order") {
        this.filterReset();
        let tagGroupIds = ["filter-by-fun", "filter-by-other"],
          categoryKey,
          categoryType;
        for (let groupId of tagGroupIds) {
          ele = document
            .getElementById(groupId)
            .getElementsByClassName("active")[0];
          categoryKey = ele.getAttribute("category-key");
          categoryType = ele.getAttribute("category-type");
          if (categoryType != null && categoryType != "0") {
            this.servicesFilted.data = [
              ...this.filterBy(
                this.servicesFilted.data,
                categoryType,
                categoryKey
              )
            ];
            this.servicesFilted.total = this.servicesFilted.data.length;
          }
        }
      } else {
        ele = document.getElementById(id).getElementsByClassName("active")[0];
        let orderKey = ele.getAttribute("order-key");
        this.servicesFilted.data = [
          ...this.orderBy(this.servicesFilted.data, orderKey)
        ];
      }
    },
    // 过滤器重置
    filterReset() {
      this.servicesFilted.data = [...this.services.data];
      this.servicesFilted.total = this.servicesFilted.data.length;
      this.servicesFilted.currPage = 1;
    },
    // 是否被收藏
    isCollection(id) {
      if (this.find(this.collections, id).length > 0) {
        return true;
      }
      return false;
    },
    // 收藏服务
    collectionServEvent(id) {
      if (this.isCollection(id)) {
        this.$Message.warning("已收藏，可以在我的收藏中取消收藏。");
        return;
      } else {
        this.collections.push(id);
        this.$Message.success("收藏成功。");
      }
    }
  }
};
</script>
<style lang="less">
.ivu-carousel {
  &.home-carousel {
    .ivu-carousel-arrow {
      border-radius: 0;
      width: 200px;
      height: 200px;

      line-height: 220px;

      background: none;
      &.left {
        left: 50%;

        margin-left: -680px;
      }
      &.right {
        right: 50%;

        margin-right: -680px;
      }
      &:hover {
        .ivu-icon {
          opacity: 1;
        }
      }
      .ivu-icon {
        font-size: 20px;

        color: #fff;

        opacity: 0.5;
      }
    }
    .ivu-carousel-dots {
      li {
        button {
          background-color: #fff;
        }
      }
    }
    .ivu-carousel-list {
      width: 100%;
    }
  }
}
</style>
<style scoped lang="less">
.layout {
  .layout-content {
    border-radius: 4px;
    min-height: 240px;

    background: #fff;
    .ivu-carousel {
      &.home-carousel {
        .ivu-carousel-item {
          .demo-carousel {
            position: relative;

            height: 340px;
            img {
              position: absolute;
              top: 50%;
              left: 50%;

              margin-top: -170px;
              margin-left: -1275px;
              width: 2550px;
              height: 340px;
            }
          }
        }
      }
    }
    .service-filter {
      margin: 0 auto 20px;
      width: 1200px;
      min-height: 787px;
      .filter-header {
        .by-fun {
          margin: 20px 0 10px;
          .ivu-tag {
            margin-right: 20px;
            border-color: #44b549;
            border-radius: 14px;
            padding: 3px 25px;
            height: 28px;

            font-size: 14px;
            line-height: 22px;

            color: #44b549;
            background: none;
            &.active {
              color: #fff;
              background-color: #44b549;
            }
          }
        }
        .by-ohter,
        .order-by {
          margin: 5px 0;
          .label {
            vertical-align: middle;
          }
          .ivu-tag {
            &.active {
              border-color: #44b549;

              color: #44b549;
            }
          }
        }
        .by-other {
          overflow: hidden;
          .label {
            float: left;

            line-height: 32px;
          }
          .content {
            float: left;
            overflow: hidden;

            margin-left: 3px;
            width: 90%;
            &.show-all-dep {
              height: 30px;
            }
            .ivu-tag {
              &.active {
                border-color: #44b549;

                color: #44b549;
              }
            }
          }
          .show-all-ctrl {
            float: right;

            margin: 5px 0;
            border: 1px solid #666;
            border-radius: 2px;
            width: 16px;
            height: 16px;

            line-height: 16px;
            text-align: center;

            color: #44b549;
          }
        }
      }
      .filter-body {
        position: relative;

        margin: 40px 0 0;
        min-height: 590px;
        .service-card {
          margin-right: 15px;
          margin-bottom: 15px;
          border-radius: 40px 0 40px 0;
          width: 228px;
          &:nth-child(5n) {
            margin-right: 0;
          }
        }
      }
      .filter-footer {
        .ivu-page {
          margin: 20px 0;

          text-align: center;
        }
      }
    }
    .news {
      overflow: hidden;

      margin: 20px auto;
      width: 1200px;
      .home-list {
        float: left;

        width: 50%;
        dt {
          padding: 15px 0;

          font-size: 16px;
          font-weight: 600;
        }
        dd {
          margin-bottom: 10px;
          width: 100%;
          a {
            display: block;
            overflow: hidden;

            font-size: 14px;

            color: #999;
            .ivu-icon {
              float: left;

              margin-right: 10px;

              line-height: 21px;

              color: #ccc;
            }
            .title {
              float: left;
              overflow: hidden;

              width: 470px;

              white-space: nowrap;
              text-overflow: ellipsis;
            }
            .time {
              float: right;

              color: #ccc;
            }
            &:hover {
              color: #444;
            }
          }
        }
      }
    }
  }
}
</style>