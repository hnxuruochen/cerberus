(ns cerberus.multi-lang.zh-CN)

(def tconfig-map {
                  :common {:delete "删除"}
                  :core {
                         :clients "客户"
                         :configuration "设置"
                         :datasets "镜像"
                         :groupings "编组"
                         :hypervisors "物理机"
                         :ipranges "IP范围"
                         :logout "登出"
                         :machines "虚机"
                         :networks "网络"
                         :notifications "通知"
                         :orgs "组织"
                         :packages "配置包"
                         :roles "角色"
                         :users "用户" 
                  }
                  :clients {:clients "客户端"}
                  :clients-view {:auth-change-secret "修改密钥"
                                 :auth-new-secret "新密钥"
                                 :auth-confirm-secret "确认密钥"
                                 :auth-change "修改"

                                 :uri-add-redirect-uri "添加重定向URI"

                                 :sections-auth "身份验证"
                                 :sections-permissions "权限"
                                 :sections-uri "URI"
                                 :sections-metadata "元数据"}
                  :clients-api {:client-delete-succ "客户端删除成功"
                                :client-delete-fail "客户端删除失败"
                                :grant-fail "权限授予失败"
                                :grant-succ "权限授予成功"
                                :revoke-succ "权限撤销成功"
                                :revoke-fail "权限撤销失败"
                                :secret-succ "密钥修改成功"
                                :secret-fail "密钥修改失败"
                                :uri-add-succ "URI添加成功"
                                :uri-add-fail "URI添加失败"
                                :uri-del-succ "URI删除成功"
                                :uri-del-fail "URI删除失败"}
                  :clients-create {:name "名称"
                                   :secret "密钥"}

                  :datasets {:datasets "系统镜像"
                             :version "版本"
                             :imported "导入"}
                  :datasets-api {:dataset-del-succ "系统镜像删除成功"
                                 :dataset-del-fail "系统镜像删除失败"
                                 :dataset-import-start "系统镜像导入开始"
                                 :dataset-import-fail "系统镜像导入失败"
                                 :nic-add-succ "网卡添加成功"
                                 :nic-add-fail "网卡添加失败"
                                 :nic-del-succ "网卡删除成功"
                                 :nic-del-fail "网卡删除失败"}
                  :datasets-create {:name "名称"
                                    :version "版本"
                                    :type "类型"
                                    :published "发布"
                                    :size "大小"}
                  :datasets-view {:home-type "类型："
                                  :home-version "版本："
                                  :networks-desc "描述"
                                  :networks-add "添加"

                                  :sections-general "常规"
                                  :sections-requirements "需求"
                                  :sections-networks "网络"
                                  :sections-metadata "元数据"}

                  :groupings {:groupings "编组"
                              :name "名称"
                              :type "类型"
                              :elements "元素"}
                  :groupings-view {:config-config "配置"
                                   :config-value "值"
                                   :config-set-config "设置"

                                   :general-general "常规"
                                   :general-name "名称"
                                   :general-type "类型"

                                   :element-add "添加"

                                   :sections-general "常规"
                                   :sections-elements "元素"
                                   :sections-configuration "配置"
                                   :sections-metadata "元数据"}
                  :groupings-create {:type "类型"
                                     :cluster "集群"
                                     :stack "堆叠"
                                     :name "名称"}
                  :groupings-api {:grouping-del-succ "编组删除成功"
                                  :grouping-del-fail "编组删除失败"
                                  :config-set-succ "配置设置成功"
                                  :config-set-fail "配置设置失败"
                                  :config-del-succ "配置删除成功"
                                  :config-del-fail "配置删除失败"
                                  :element-add-succ "元素添加成功"
                                  :element-add-fail "元素添加失败"
                                  :element-del-succ "元素删除成功"
                                  :element-del-fail "元素删除失败"}

                  :roles {:roles "角色"}
                  :roles-view {:general "常规"
                               :permissions "权限"
                               :metadata "元数据"}
                  :roles-api {:delete-succ "角色删除成功"
                              :delete-fail "角色删除失败"
                              :grant-succ "权限授予成功"
                              :grant-fail "权限授予失败"
                              :revoke-succ "权限撤销成功"
                              :revoke-fail "权限撤销失败"}
                  :roles-create {:name "名称"}})
