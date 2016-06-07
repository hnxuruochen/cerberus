(ns cerberus.multi-lang.zh-CN)

(def tconfig-map {
                  :common {:delete "删除"}

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

                  :hypervisors {:hypervisors "物理机"
                                :name "别名"
                                :version "版本"
                                :os-ver "操作系统版本"
                                :host "主机"
                                :last-seen "最后上线"
                                :uptime "运行时间"
                                :used-mem "使用内存"
                                :reserved-mem "保留内存"
                                :free-mem "空闲内存"}
                  :hypervisors-api {:hv-del-succ "物理机删除成功"
                                    :hv-del-fail "物理机删除失败"
                                    :hv-rename-succ "物理机重命名成功"
                                    :hv-rename-fail "物理机重命名失败"
                                    :svc-change-succ "服务改变状态成功"
                                    :svc-change-fail "服务改变状态失败"
                                    :config-set-succ "配置设置成功"
                                    :config-set-fail "配置设置失败"
                                    :char-set-succ "特性设置成功"
                                    :char-set-fail "特性设置失败"
                                    :char-del-succ "特性删除成功"
                                    :char-del-fail "特性删除失败"}
                  :hypervisors-view {:gen-info "信息"
                                     :gen-info-host "主机"
                                     :gen-info-os "操作系统"
                                     :gen-info-os-ver "操作系统版本"
                                     :gen-info-chunter-ver "Chunter版本"
                                     :gen-info-last-boot "最后启动"

                                     :gen-hw-hardware "硬件"
                                     :gen-hw-cpu "处理器"
                                     :gen-hw-cores "核心"
                                     :gen-hw-mainboard "主板"
                                     :gen-hw-manufacturer "制造商"
                                     :gen-hw-sn "序列号"
                                     :gen-hw-vs "虚拟化支持"

                                     :gen-mem-memory "内存"
                                     :gen-mem-total "总量"
                                     :gen-mem-provisioned "已分配"
                                     :gen-mem-free "空闲"
                                     :gen-mem-reserved "保留"
                                     :gen-mem-l1-size "L1缓存大小"
                                     :gen-mem-l1-hit "L1缓存命中率"

                                     :gen-st-storage "存储"
                                     :gen-st-disks "磁盘"
                                     :gen-st-pools "池"
                                     :gen-st-health "健康: "
                                     :gen-st-size "大小: "
                                     :gen-st-free "空闲: "
                                     :gen-st-used "已使用: "

                                     :gen-home-unknown "未知"
                                     :gen-home-change-alias "修改别名"
                                     :gen-home-networks "网络"

                                     :char-char "特性"
                                     :char-value "值"
                                     :char-add-char "添加特性"

                                     :metrics-cpu "处理器"

                                     :sections-general "常规"
                                     :sections-services "服务"
                                     :sections-char "特性"
                                     :sections-metrics "度量"
                                     :sections-metadata "元数据"}


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
