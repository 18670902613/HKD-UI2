
<template>
    <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
        <header-panel 
            :visitor="visitor">
        </header-panel>
        <Row type="flex">
            <Col :span="spanLeft">             
                <Menu :theme="spanLeft == 5 ? 'light' : 'dark'" 
                ref="leftMenu"
                class="layout-menu-left" 
                :class="{ 'menu-hide-text': spanLeft != 5}"
                :active-name="leftMenuActiveName" 
                :open-names="leftMenuOpenNames" 
                width="auto" 
                :style="{ 'height': leftMenuHeight + 'px' }"
                accordion>
                    <template v-for="(menu, index) in leftMenuData.data">
                        <Submenu 
                            v-if="menu.leaf"  
                            :title="menu.text"                           
                            :name="index + 1">
                            <template slot="title">
                                <Icon class="menu-icon" :type="menu.icon" :size="iconSizeMenu" ></Icon>                         
                                <span class="menu-text">{{ menu.text }}</span>                        
                            </template>
                            <Menu-item                                   
                                v-for="(subMenu, subMenuIndex) in menu.subMenu.data"
                                :title="subMenu.text"
                                :key="subMenu.id" 
                                :name="(index + 1) + '-' + (subMenuIndex + 1)">
                                <router-link :to="subMenu.router">
                                    <Icon class="submenu-icon" :type="subMenu.icon" :size="iconSizeSubMenu"></Icon> 
                                    <span class="menu-text">{{ subMenu.text }}</span>
                                </router-link>
                            </Menu-item>
                        </Submenu>
                        <Menu-item  
                            v-else
                            :title="menu.text"
                            :name="index + 1">
                            <router-link :to="menu.router">
                                <Icon class="menu-icon" :type="menu.icon" :size="iconSizeMenu"></Icon> 
                                <span class="menu-text">{{ menu.text }}</span>
                            </router-link>
                        </Menu-item>
                    </template>
                </Menu>             
            </Col>
            <Col :span="spanRight">
                <Button type="text" @click="toggleClick" class="btn-menu-slider" :class="{ 'packup': spanLeft != 5 }">
                    <Icon :type="spanLeft == 5 ? 'android-arrow-dropleft' : 'android-arrow-dropright'" size="12"></Icon>
                </Button>
                <div class="layout-breadcrumb">
                    <Breadcrumb>
                        <BreadcrumbItem 
                            v-for="breadcrumbItem in breadcrumb" 
                            key="breadcrumbItem.id" 
                            :href="breadcrumbItem.router">
                            {{ breadcrumbItem.text }}
                        </BreadcrumbItem>                        
                    </Breadcrumb>
                </div>
                <div class="layout-content" :style="{'height': layoutHeight + 'px'}">
                    <div class="layout-content-main">
                        <transition name="slide-fade">
                            <router-view></router-view>
                        </transition>
                    </div>
                    <div class="layout-copy">
                        2017 &copy; Ben
                    </div>
                </div>
            </Col>
        </Row>
    </div>
</template>
<script>
import { getLeftMenuData } from "../../../data/getData.js";
import headerPanel from "../../components/headerPanel.vue";
// import { Utils } from '../../lib/utils.js';
export default {
  components: {
    headerPanel
  },
  created() {
    // 根据路由传入的服务ID请求服务的左侧菜单
    this.getLeftMenuData(this.$route.params.serviceID).then(leftMenuData => {
      this.leftMenuData.serviceID = leftMenuData.data.serviceID;
      this.leftMenuData.serviceName = leftMenuData.data.serviceName;
      this.leftMenuData.data = [...leftMenuData.data.data];
      // 维护面包屑导航
      this.breadcrumb.push({
        text: this.leftMenuData.serviceName,
        router:
          "/service/" +
          this.leftMenuData.serviceID +
          "/" +
          this.leftMenuData.data[0].subMenu.data[0].router
      });
      this.breadcrumb.push({
        text: this.leftMenuData.data[0].subMenu.data[0].text,
        router: this.leftMenuData.data[0].subMenu.data[0].router
      });
      // 手动刷新左侧菜单的打开想和激活项
      this.leftMenuActiveName = "1-1";
      this.leftMenuOpenNames = [1];
      this.$nextTick(() => {
        this.$refs.leftMenu.updateOpened();
        this.$refs.leftMenu.updateActiveName();
        this.$router.push({ name: "page1" });
      });
    });
  },
  mounted() {},
  data() {
    return {
      spanLeft: 5, // 左侧菜单宽度
      spanRight: 19, // 内容区域宽度
      visitor: false, // 登录状态
      getLeftMenuData: getLeftMenuData, // 请求左侧菜单列表的方法
      // 左侧菜单数据
      leftMenuData: {
        serviceID: "",
        serviceName: "",
        data: []
      },
      // 左侧菜单打开项
      leftMenuOpenNames: [],
      // 左侧菜单激活项
      leftMenuActiveName: "",
      // 面包屑数据
      breadcrumb: [
        {
          text: "首页",
          router: "/home"
        }
      ]
    };
  },
  computed: {
    // 一级菜单图标大小
    iconSizeMenu() {
      return this.spanLeft === 5 ? 14 : 24;
    },
    // 二级菜单图标大小
    iconSizeSubMenu() {
      return this.spanLeft === 5 ? 10 : 16;
    },
    // 左侧菜单最小高度
    leftMenuHeight() {
      return window.innerHeight - 64 - 5;
    },
    // 主体内容区域最小高度
    layoutHeight() {
      return window.innerHeight - 64 - 31 - 5;
    }
  },
  methods: {
    // 左侧菜单展开/收起
    toggleClick() {
      if (this.spanLeft === 5) {
        this.spanLeft = 2;
        this.spanRight = 22;
      } else {
        this.spanLeft = 5;
        this.spanRight = 19;
      }
    }
  }
};
</script>
<style lang="less">
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-enter {
  transform: translateX(-100%);
}
.layout-menu-left {
  .ivu-menu-item {
    a {
      color: #495060;
      .menu-icon {
        margin-right: 8px;
      }
    }
  }
  &.ivu-menu-vertical .ivu-menu-item {
    padding: 0;
    a {
      display: block;
      padding: 14px 24px;
    }
  }
  &.ivu-menu-vertical .ivu-menu-submenu .ivu-menu-item {
    a {
      padding-left: 43px;
    }
  }
  &.menu-hide-text {
    .ivu-menu-item,
    .ivu-menu-submenu-title {
      .menu-text,
      .ivu-menu-submenu-title-icon {
        display: none;
      }
    }
    .ivu-menu-item {
      a {
        color: rgba(255, 255, 255, 0.7);
        &:hover {
          color: #fff;
        }
      }
    }
  }
}
</style>

<style scoped lang="less">
.layout {
  .ivu-col {
    transition: width 0.2s ease-in-out;
  }
  .btn-menu-slider {
    position: absolute;
    top: 0;
    left: -1px;
    z-index: 999;

    border: 1px solid #e5e5e5;
    border-radius: 0 15px 15px 0;
    padding: 5px;

    font-size: 12px;

    background-color: #fff;
    box-shadow: 1px 1px 3px #e5e5e5;
    &:hover {
      .ivu-icon {
        color: #44b549;
      }
    }
    &.packup {
      background-color: #495060;
      color: #fff;
      border-color: #495060;
    }
  }
  .layout-breadcrumb {
    padding: 5px 15px;

    background-color: #f8f8f9;
  }
  .layout-content {
    overflow-y: auto;

    border-radius: 4px;
    min-height: 200px;

    background-color: #f8f8f9;
    .layout-content-main {
      padding: 15px;
    }
    .layout-copy {
      padding: 20px 0;
      margin-top: 15px;

      text-align: center;

      color: #9ea7b4;
      background-color: #fff;
    }
  }
}
</style>