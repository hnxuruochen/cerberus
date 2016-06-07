(ns cerberus.multi-lang.zh-CN)

(def tconfig-map {
                  :missing "翻译缺失"
                  :core {
                         :clients "客户"
                         :configuration "设置"
                         :datasets "镜像"
                         :groupings "编组"
                         :hypervisors "物理机"
                         :ip-ranges "网络地址范围"
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
                                     :gen-st-used "使用: "

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

                  :ipranges {:ip-ranges "网络地址范围"}
                  :ipranges-api {:delete-succ "网络地址范围删除成功"
                                 :delete-fail "网络地址范围删除失败"}
                  :ipranges-create {:name "名称"
                                    :nic-tag "网卡标签"
                                    :vlan "虚拟局域网"
                                    :subnet-ip "子网网络地址"
                                    :netmask "子网掩码"
                                    :gateway "网关"
                                    :first "首地址"
                                    :last "尾地址"}
                  :ipranges-view {:gen-general-general "常规"
                                  :gen-general-uuid "标识符"
                                  :gen-general-network "网络"
                                  :gen-general-gateway "网关"
                                  :gen-general-netmask "子网掩码"
                                  :gen-general-vlan "虚拟局域网"
                                  :gen-general-tag "标签"

                                  :gen-ips-ips "网络地址"
                                  :gen-ips-free "空闲"
                                  :gen-ips-used "使用"

                                  :ips-free "空闲"
                                  :ips-used "使用"

                                  :sections-general "常规"
                                  :sections-ips "网络地址"
                                  :sections-metadata "元数据"}

                  :networks {:networks "网络配置"
                             :ip-ranges "网络地址范围"}
                  :networks-api {:network-del-succ "网络配置删除成功"
                                 :network-del-fail "网络配置删除失败"
                                 :iprange-add-succ "网络地址范围添加成功"
                                 :iprange-add-fail "网络地址范围添加失败"
                                 :iprange-del-succ "网络地址范围删除成功"
                                 :iprange-del-fail "网络地址范围删除失败"}
                  :networks-create {:name "名称"}
                  :networks-view {:iprange-add "添加"
                                  :gen-general "常规"
                                  :gen-uuid "UUID"
                                  :gen-ipranges "网络地址范围"
                                  :sections-general "常规"
                                  :sections-ipranges "网络地址范围"
                                  :sections-metadata "元数据"}

                  :orgs {:orgs "组织"}
                  :orgs-api {:orgs-del-succ "组织删除成功"
                             :orgs-del-fail "组织删除失败"
                             :trigger-del-succ "触发器删除成功"
                             :trigger-del-fail "触发器删除失败"
                             :trigger-add-succ "触发器添加成功"
                             :trigger-add-fail "触发器添加失败"
                             :res-dec-succ "资源减少成功"
                             :res-dec-fail "资源减少失败"
                             :res-inc-succ "资源增加成功"
                             :res-inc-fail "资源增加失败"
                             :net-set-succ "网络配置设置成功"
                             :net-set-fail "网络配置设置失败"
                             :res-del-succ "资源删除成功"
                             :res-del-fail "资源删除失败"}
                  :orgs-create {:name "名称"}
                  :orgs-view {:res-res "资源"
                              :res-value "值"
                              :res-inc "增加"
                              :res-dec "减少"

                              :docker-set-pub-net "设置公开网络"
                              :docker-set-priv-net "设置私有网络"

                              :tr-trigger-vm "当新建虚机"
                              :tr-trigger-user "当新建用户"
                              :tr-trigger-dataset "当新建镜像"

                              :tr-rest-role "授予角色"
                              :tr-rest-user "授予用户"
                              :tr-rest-join-org "加入组织"
                              :tr-rest-rcv-role "获得角色"

                              :tr-when "当"
                              :tr-a-vm "一台虚机"
                              :tr-a-user "一名用户"
                              :tr-a-dataset "一个镜像"
                              :tr-is-created "被创建"
                              :tr-join-them-org "将他们加入组织"
                              :tr-give-them-role "授予他们角色"
                              :tr-grant-role "授予角色"
                              :tr-grant-user "授予用户"
                              :tr-permissions-to "权限去"
                              :tr-the-new "该新"
                              :tr-vm "虚机"
                              :tr-user "用户"
                              :tr-dataset "镜像"
                              :tr-period "。"
                              :tr-create-trigger "创建触发器"
                              :tr-event "事件"
                              :tr-rest "其他"

                              :gen-general "常规"
                              :gen-uuid "UUID"
                              :gen-res "资源"
                              :gen-triggers "触发器"
                              :gen-tr-total "总计"
                              :gen-tr-vm "虚机创建"
                              :gen-tr-user "用户创建"
                              :gen-tr-dataset "镜像创建"

                              :sections-general "常规"
                              :sections-res "资源"
                              :sections-accounting "账务"
                              :sections-triggers "触发器"
                              :sections-docker "Docker"
                              :sections-metadata "元数据"}

                  :packages {:action-clone "克隆"
                             :pkgs "配置包"
                             :ttl-cpu "CPU"
                             :ttl-quota "配额"
                             :ttl-ram "内存"}
                  :packages-api {:del-succ "配置包删除成功"
                                 :del-fail "配置包删除失败"}
                  :packages-create {:cond-must "必须"
                                    :cond-cant "不能"
                                    :cond-scale "缩放"
                                    :cond-rand "随机"

                                    :lbl-name "名称"
                                    :lbl-cpu "CPU"
                                    :lbl-mem "内存"
                                    :lbl-disk "磁盘"
                                    :lbl-io-prio "IO优先级"
                                    :lbl-blk-size "块大小"
                                    :lbl-compr "压缩"

                                    :rules-rules "规则"
                                    :rules-attr "属性"
                                    :rules-val "值"
                                    :rules-low "低"
                                    :rules-high "高"
                                    :rules-add "添加"

                                    :org-res "组织资源"
                                    :org-res-res "资源"
                                    :org-res-req "需求"
                                    :org-res-add "添加"
                                    :org-res-val "值"}
                  :packages-view {:gen-general "常规"
                                  :gen-uuid "UUID"
                                  :gen-req "需求"
                                  :gen-cpu-mem "CPU / 内存"
                                  :gen-ram "内存"
                                  :gen-cpu-cap "CPU性能"
                                  :gen-cpu-shares "CPU共享"
                                  :gen-disk "磁盘"
                                  :gen-disk-quota "配额"
                                  :gen-disk-compr "压缩"
                                  :gen-disk-io-prio "IO优先级"
                                  :gen-disk-blk-size "块大小"
                                  :gen-org-res "组织资源"

                                  :req-scale "缩放"
                                  :req-rand "随机"
                                  :req-between "介于区间"
                                  :req-and "至"

                                  :sections-general "常规"
                                  :sections-req "需求"
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
                  :roles-create {:name "名称"}
                  :vms {
                        :brand "类型"
                        :cluster "集群"
                        :console "控制台"
                        :cpu "处理器"
                        :created "创建时间"
                        :created-ago "创建时长"
                        :creator "创建者"
                        :dataset "镜像"
                        :delete "删除"
                        :failed "已失败"
                        :hostname "主机名"

                        :hypervisor "物理机"
                        :ip "网络地址"
                        :lock "锁定"
                        :machines "虚机"
                        :memory "内存"
                        :name "名称"
                        :owner "所有者"
                        :package "配置包"
                        :reboot "重启"
                        :running "运行中"
                        :start "启动"
                        :state "状态"
                        :stop "停止"
                        :stopped "已停止"
                        :unlock "解锁"
                        }})
