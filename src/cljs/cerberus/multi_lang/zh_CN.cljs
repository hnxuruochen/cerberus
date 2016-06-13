(ns cerberus.multi-lang.zh-CN)

(def tconfig-map {
                  :add {
                        :new-machine "创建新的虚拟机"
                        :new-users "创建用户"
                        :new-roles "创建新角色"
                        :new-orgs "创建新组织"
                        :new-clients "创建新客户端"
                        :new-groupings "创建新编组"
                        :new-packages "创建新的配置包"
                        :new-networks "创建新的网络"
                        :new-ipranges "创建新的IP范围"
                        :new-dtrace "创建新 DTrace Script"
                        :new-datasets "导入镜像"
                        :btn-machine "创建虚拟机"
                        :btn-users "创建用户"
                        :btn-roles "创建角色"
                        :btn-orgs "创建组织"
                        :btn-clients "创建客户端"
                        :btn-groupings "创建编组"
                        :btn-packages "创建配置包"
                        :btn-networks "创建网络"
                        :btn-ipranges "创建IP范围"
                        :btn-dtrace "创建 DTrace Script"
                        :btn-datasets "导入"
                        }
                  :core {
                         :clients "客户端"
                         :configuration "配置"
                         :datasets "镜像"
                         :groupings "编组"
                         :hypervisors "物理机"
                         :ip-ranges "IP范围"
                         :login "登入"
                         :logout "登出"
                         :machines "虚拟机"
                         :networks "网络"
                         :notifications "通知"
                         :orgs "组织"
                         :packages "配置包"
                         :password "密码"
                         :roles "角色"
                         :users "用户"
                         :yubikey "YubiKey"
                         }
                  :clients {
                            :clients "客户端"
                            }
                  :clients-view {
                                 :auth-change-secret "修改密钥"
                                 :auth-new-secret "新密钥"
                                 :auth-confirm-secret "确认密钥"
                                 :auth-change "修改"
                                 :uri-add-redirect-uri "添加重定向URI"
                                 :sections-auth "身份验证"
                                 :sections-permissions "权限"
                                 :sections-uri "URI"
                                 :sections-metadata "元数据"
                                 }
                  :clients-api {
                                :client-delete-succ "客户端删除成功。"
                                :client-delete-fail "客户端删除失败。"
                                :grant-fail "权限授予失败。"
                                :grant-succ "权限授予成功。"
                                :revoke-succ "权限撤销成功。"
                                :revoke-fail "权限撤销失败。"
                                :secret-succ "密钥修改成功。"
                                :secret-fail "密钥修改失败。"
                                :uri-add-succ "URI添加成功。"
                                :uri-add-fail "URI添加失败。"
                                :uri-del-succ "URI删除成功。"
                                :uri-del-fail "URI删除失败。"
                                }
                  :clients-create {
                                   :name "名称"
                                   :secret "密钥"
                                   }
                  :datasets {
                             :datasets "镜像"
                             :version "版本"
                             :imported "导入"
                             }
                  :datasets-api {
                                 :dataset-del-succ "镜像删除成功。"
                                 :dataset-del-fail "镜像删除失败。"
                                 :dataset-import-start "镜像导入开始"
                                 :dataset-import-fail "镜像导入失败。"
                                 :nic-add-succ "网卡添加成功。"
                                 :nic-add-fail "网卡添加失败。"
                                 :nic-del-succ "网卡删除成功。"
                                 :nic-del-fail "网卡删除失败。"
                                 }
                  :datasets-create {
                                    :name "名称"
                                    :version "版本"
                                    :type "类型"
                                    :published "发布"
                                    :size "大小"
                                    }
                  :datasets-view {
                                  :home-type "类型："
                                  :home-version "版本："
                                  :networks-desc "描述"
                                  :networks-add "添加"
                                  :sections-general "常规"
                                  :sections-requirements "需求"
                                  :sections-networks "网络"
                                  :sections-metadata "元数据"
                                  }
                  :groupings {
                              :groupings "编组"
                              :name "名称"
                              :type "类型"
                              :elements "元素"
                              }
                  :groupings-view {
                                   :config-config "配置"
                                   :config-value "值"
                                   :config-set-config "设置"
                                   :general-general "常规"
                                   :general-name "名称"
                                   :general-type "类型"
                                   :element-add "添加"
                                   :sections-general "常规"
                                   :sections-elements "元素"
                                   :sections-configuration "配置"
                                   :sections-metadata "元数据"
                                   }
                  :groupings-create {
                                     :type "类型"
                                     :cluster "集群"
                                     :stack "堆叠"
                                     :name "名称"
                                     }
                  :groupings-api {
                                  :grouping-del-succ "编组删除成功。"
                                  :grouping-del-fail "编组删除失败。"
                                  :config-set-succ "配置设置成功。"
                                  :config-set-fail "配置设置失败。"
                                  :config-del-succ "配置删除成功。"
                                  :config-del-fail "配置删除失败。"
                                  :element-add-succ "元素添加成功。"
                                  :element-add-fail "元素添加失败。"
                                  :element-del-succ "元素删除成功。"
                                  :element-del-fail "元素删除失败。"
                                  }
                  :hypervisors {
                                :hypervisors "物理机"
                                :name "别名"
                                :version "版本"
                                :os-ver "操作系统版本"
                                :host "主机"
                                :last-seen "最后上线"
                                :uptime "运行时间"
                                :used-mem "使用内存"
                                :reserved-mem "保留内存"
                                :free-mem "空闲内存"
                                }
                  :hypervisors-api {
                                    :hv-del-succ "物理机删除成功。"
                                    :hv-del-fail "物理机删除失败。"
                                    :hv-rename-succ "物理机重命名成功。"
                                    :hv-rename-fail "物理机重命名失败。"
                                    :svc-change-succ "服务改变状态成功。"
                                    :svc-change-fail "服务改变状态失败。"
                                    :config-set-succ "配置设置成功。"
                                    :config-set-fail "配置设置失败。"
                                    :char-set-succ "特性设置成功。"
                                    :char-set-fail "特性设置失败。"
                                    :char-del-succ "特性删除成功。"
                                    :char-del-fail "特性删除失败。"
                                    }
                  :hypervisors-view {
                                     :gen-info "信息"
                                     :gen-info-host "主机"
                                     :gen-info-os "操作系统"
                                     :gen-info-os-ver "操作系统版本"
                                     :gen-info-chunter-ver "Chunter版本"
                                     :gen-info-last-boot "最后启动"
                                     :gen-hw-hardware "硬件"
                                     :gen-hw-cpu "CPU"
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
                                     :metrics-cpu "CPU"
                                     :sections-general "常规"
                                     :sections-services "服务"
                                     :sections-char "特性"
                                     :sections-metrics "度量"
                                     :sections-metadata "元数据"
                                     }
                  :metrics {
                            :error-info "没有配置度量数据存储，请安装DalmatinerDB和Tachyon以启用这个功能。"
                            }
                  :ipranges {
                             :ip-ranges "IP范围"
                             }
                  :ipranges-api {
                                 :delete-succ "IP范围删除成功。"
                                 :delete-fail "IP范围删除失败。"
                                 }
                  :ipranges-create {
                                    :name "名称"
                                    :nic-tag "网卡标签"
                                    :vlan "虚拟局域网"
                                    :subnet-ip "子网IP"
                                    :netmask "子网掩码"
                                    :gateway "网关"
                                    :first "首地址"
                                    :last "尾地址"
                                    }
                  :ipranges-view {
                                  :gen-general-general "常规"
                                  :gen-general-uuid "UUID"
                                  :gen-general-network "网络"
                                  :gen-general-gateway "网关"
                                  :gen-general-netmask "子网掩码"
                                  :gen-general-vlan "虚拟局域网"
                                  :gen-general-tag "标签"
                                  :gen-ips-ips "IPs"
                                  :gen-ips-free "空闲"
                                  :gen-ips-used "使用"
                                  :ips-free "空闲"
                                  :ips-used "使用"
                                  :sections-general "常规"
                                  :sections-ips "IPs"
                                  :sections-metadata "元数据"
                                  }
                  :networks {
                             :networks "网络配置"
                             :ip-ranges "IP范围"
                             }
                  :networks-api {
                                 :network-del-succ "网络配置删除成功。"
                                 :network-del-fail "网络配置删除失败。"
                                 :iprange-add-succ "IP范围添加成功。"
                                 :iprange-add-fail "IP范围添加失败。"
                                 :iprange-del-succ "IP范围删除成功。"
                                 :iprange-del-fail "IP范围删除失败。"
                                 }
                  :networks-create {
                                    :name "名称"
                                    }
                  :networks-view {
                                  :iprange-add "添加"
                                  :gen-general "常规"
                                  :gen-uuid "UUID"
                                  :gen-ipranges "IP范围"
                                  :sections-general "常规"
                                  :sections-ipranges "IP范围"
                                  :sections-metadata "元数据"
                                  }
                  :orgs {
                         :orgs "组织"
                         }
                  :orgs-api {
                             :orgs-del-succ "组织删除成功。"
                             :orgs-del-fail "组织删除失败。"
                             :trigger-del-succ "触发器删除成功。"
                             :trigger-del-fail "触发器删除失败。"
                             :trigger-add-succ "触发器添加成功。"
                             :trigger-add-fail "触发器添加失败。"
                             :res-dec-succ "资源减少成功。"
                             :res-dec-fail "资源减少失败。"
                             :res-inc-succ "资源增加成功。"
                             :res-inc-fail "资源增加失败。"
                             :net-set-succ "网络配置设置成功。"
                             :net-set-fail "网络配置设置失败。"
                             :res-del-succ "资源删除成功。"
                             :res-del-fail "资源删除失败。"
                             }
                  :orgs-create {
                                :name "名称"
                                }
                  :orgs-view {
                              :res-res "资源"
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
                  :packages-api {:del-succ "配置包删除成功。"
                                 :del-fail "配置包删除失败。"}
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
                               :metadata "元数据"
                               }
                  :roles-api {
                              :delete-succ "角色删除成功。"
                              :delete-fail "角色删除失败。"
                              :grant-succ "权限授予成功。"
                              :grant-fail "权限授予失败。"
                              :revoke-succ "权限撤销成功。"
                              :revoke-fail "权限撤销失败。"
                              }
                  :roles-create {
                                 :name "名称"
                                 }
                  :services {
                             :clear "重置"
                             :disable "禁用"
                             :disabled "已禁用"
                             :enable "启用"
                             :legacy-run "遗留运行"
                             :offline "离线"
                             :online "在线"
                             :refresh "刷新"
                             :restart "重启"
                             :service "服务"
                             :state "状态"
                             }
                  :users-view {
                               :change-password "更换密码"
                               :new-password "新密码"
                               :confirm "确认"
                               :change "更换"
                               :password-changed "密码已更换"
                               :new-ssh-key "新的ssh公钥"
                               :ssh-key "公钥"
                               :ssh-name "名称"
                               :ssh-add "添加"
                               :register-yubikey "注册YubiKey"
                               :yubi-add "添加"
                               :yubi-key "Key"
                               :ssh-keys "SSH公钥"
                               :yubi-keys "Yubi Keys"
                               :roles-add "添加"
                               :orgs-add "添加"
                               :token-api-key "API Key"
                               :token-api-key-exist-1 "您的 API key  已经被创建，这是能获取它的唯一机会，"
                               :token-api-key-exist-2 "确保把它放在申请这个API key的应用中。"
                               :token-api-key-exist-3 "一旦这个窗口关闭了你将再也看不到它了！"
                               :api-token "API 令牌"
                               :api-key-name "API Key 名称"
                               :create-api-key "创建 API Key"
                               :tokens-list-title-client "客户端"
                               :tokens-list-title-type "类型"
                               :tokens-list-title-expiry "过期时间"
                               :tokens-list-title-revoke "撤销"
                               :title-authentication "身份验证"
                               :title-permission "权限"
                               :title-roles "角色"
                               :title-orgs "组织"
                               :title-tokens "令牌"
                               :title-metadata "元数据"
                               :users "用户"
                               :api "API"
                               :name "名称"
                               :organisation "组织"
                               }
                  :users-api {
                              :delete-user-succeed "用户删除成功。"
                              :delete-user-failed "用户删除失败。"
                              :change-password-succeed "密码已更换。"
                              :change-password-failed "密码更换失败。"
                              :permission-granted "权限被授予。"
                              :grant-permission-failed "授予权限失败。"
                              :revoke-permission-succeed "权限被撤销。"
                              :revoke-permission-failed "权限撤销失败。"
                              :revoke-token-succeed "令牌被撤销。"
                              :revoke-token-failed "令牌撤销失败。"
                              :add-ssh-key-succeed "新的ssh公钥添加成功。"
                              :add-ssh-key-failed "新的ssh公钥添加失败。"
                              :add-yubi-key-succeed "Yubikey添加成功。"
                              :add-yubi-key-failed "Yubikey添加失败。"
                              :removed-ssh-key-succeed "SSH公钥被移除。"
                              :removed-ssh-key-failed "SSH公钥移除失败。"
                              :removed-yubi-key-succeed "Yubikey被移除。"
                              :removed-yubi-key-failed "Yubikey移除失败。"
                              :add-role-succeed "角色添加成功。"
                              :add-role-failed "角色添加失败。"
                              :remove-role-succeed "角色移除成功。"
                              :remove-role-failed "角色移除失败。"
                              :organisation-joined "已加入组织。"
                              :organisation-join-failed "加入组织失败。"
                              :active-organisation-succeed "组织已设置为活动状态。"
                              :active-organisation-failed "设置组织为活动状态失败。"
                              :remove-org-succeed "离开组织。"
                              :remove-org-failed "离开组织失败。"
                              }
                  :users-create {
                                 :name "用户名"
                                 :password "密码"
                                 }
                  :vms {
                        :brand "类型"
                        :cluster "集群"
                        :console "控制台"
                        :cpu "CPU"
                        :created "创建时间"
                        :created-ago "创建时长"
                        :creator "创建者"
                        :dataset "镜像"
                        :delete "删除"
                        :failed "已失败"
                        :hostname "主机名"
                        :hypervisor "物理机"
                        :installing-dataset "安装镜像中"
                        :ip "IP"
                        :limbo "已废弃"
                        :lock "锁定"
                        :machines "虚拟机"
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
                        }
                  :vms-api {
                            :backup-create "正在创建备份。"
                            :backup-create-fail "创建备份失败。"
                            :backup-delete "正在删除备份。"
                            :backup-delete-fail "删除备份失败。"
                            :backup-restore "正在从备份中还原。"
                            :backup-restore-fail "从备份中还原失败。"
                            :firewall-add "正在添加防火墙规则。"
                            :firewall-add-fail "添加防火墙规则失败。"
                            :firewall-delete "正在删除防火墙规则。"
                            :firewall-delete-fail "删除防火墙规则失败。"
                            :hostname-set "主机名已设置。"
                            :hostname-set-fail "设置主机名失败。"
                            :network-add "正在添加网络。"
                            :network-add-fail "添加网络失败。"
                            :network-delete "正在删除网络。"
                            :network-delete-fail "删除网络失败。"
                            :network-marking "正在标记主要网络。"
                            :network-marking-fail "标记主要网络失败。"
                            :owner-change "所有者已更换。"
                            :owner-change-fail "更换所有者失败。"
                            :service-change "正在变更服务状态。"
                            :service-change-fail "变更服务状态失败。"
                            :snapshot-create "正在创建快照。"
                            :snapshot-create-fail "创建快照失败。"
                            :snapshot-delete "正在删除快照。"
                            :snapshot-delete-fail "删除快照失败。"
                            :snapshot-restore "正在从快照还原。"
                            :snapshot-restore-fail "从快照还原失败。"
                            :vm-conf-change "正在变更虚拟机设置。"
                            :vm-conf-change-fail "变更虚拟机设置失败。"
                            :vm-del-fail "删除虚拟机失败。"
                            :vm-del-succ "成功删除虚拟机。"
                            :vm-reboot "正在重启虚拟机。"
                            :vm-reboot-fail "重启虚拟机失败。"
                            :vm-rm-fail "从物理机上删除虚拟机失败。"
                            :vm-rm-succ "成功从物理机上删除虚拟机。"
                            :vm-pkg-change "正在变更虚拟机配置包。"
                            :vm-pkg-change-fail "变更虚拟机配置包失败，有可能剩余空间不足。"
                            :vm-start "正在启动虚拟机。"
                            :vm-start-fail "启动虚拟机失败。"
                            :vm-stop "正在停止虚拟机。"
                            :vm-stop-fail "停止虚拟机失败。"
                            }
                  :vms-create {
                               :advanced "高级"
                               :alias "别名"
                               :cpu "CPU"
                               :dataset "镜像"
                               :general "常规"
                               :hostname "主机名"
                               :key "键"
                               :metadata "元数据"
                               :name "名称"
                               :network "网络"
                               :networking "网络"
                               :package "配置包"
                               :quota "配额"
                               :ram "内存"
                               :set-resolvers "设置域名解析地址"
                               :value "值"
                               :version "版本"
                               :add "添加"
                               }
                  :vms-view {
                             :action "动作"
                             :add "添加"
                             :add-rule "添加规则"
                             :alias "别名"
                             :all "所有"
                             :allow "允许"
                             :all-ports "所有端口"
                             :autoboot "自动启动"
                             :backups "备份"
                             :backup-comment "备份描述"
                             :backup-delete "从物理机上删除"
                             :backup-info "备份创建完成以后，可以在不删除备份的前提下将虚拟机从物理机上删除，然后利用备份重新部署虚拟机。"
                             :block "阻塞"
                             :change "更换"
                             :change-alias "更换别名"
                             :comment "描述"
                             :completed "已完成"
                             :cpu "CPU"
                             :cpu- "CPU："
                             :cpu-cap "CPU上限"
                             :cpu-memory "CPU/内存"
                             :cpu-shares "CPU共享"
                             :create "创建"
                             :created "创建时间"
                             :create-image "创建镜像"
                             :custom "定制"
                             :dataset "镜像"
                             :date "日期"
                             :delete "删除"
                             :deploy-info "这台虚拟机没有部署在任何物理机上，这意味着它不能被重新部署。"
                             :description "描述"
                             :destination "目的"
                             :dest-ip "目的IP"
                             :direction "方向"
                             :disk "磁盘"
                             :dns-domain "域名解析域"
                             :dst "目的"
                             :entry "条目"
                             :failed "已失败"
                             :firewall "防火墙"
                             :firewall-rules "防火墙规则"
                             :gateway- "网关："
                             :general "常规"
                             :hostname "主机名"
                             :hostname- "主机名："
                             :hypervisor "物理机"
                             :image-info-0 "按照以下步骤创建镜像："
                             :image-info-1 "确保虚拟机一切服务运行正常"
                             :image-info-2 "执行指令sm-prepare-image让虚拟机准备制作镜像"
                             :image-info-3 "为虚拟机创建一个快照"
                             :image-info-4 "填写表格，完善镜像基本信息"
                             :image-info-5 "选择一个你想制作成镜像的快照"
                             :image-info-6 "然后等待镜像制作完成，页面上可以看到状态。完成后，就可以用这个镜像创建虚拟机了。更多的信息"
                             :image-info-7 "在这里"
                             :image-info-8 "。"
                             :imaging "镜像"
                             :inbound "入站"
                             :inbound-rules "入站规则"
                             :incremental "增量备份"
                             :io-priority "读写优先级"
                             :ip "IP"
                             :ips "IPs"
                             :ip- "IP："
                             :ip-range- "IP范围："
                             :logs "日志"
                             :mac- "网卡物理地址："
                             :mask "掩码"
                             :max-swap "最大交换区"
                             :memory "内存"
                             :memory- "内存："
                             :metadata "元数据"
                             :metrics "度量"
                             :name "名称"
                             :netmask- "子网掩码："
                             :networking "网络"
                             :networks "网络"
                             :network- "网络："
                             :new-backup "新建备份"
                             :new-snapshot "新建快照"
                             :os "操作系统"
                             :outbound "出站"
                             :outbound-rules "出站规则"
                             :owner "所有者"
                             :package "配置包"
                             :ports "端口"
                             :protocol "协议"
                             :quota "配额"
                             :quota- "配额："
                             :resolvers "域名解析地址"
                             :restore "还原"
                             :roll-back "回滚"
                             :services "服务"
                             :set-owner "设置所有者"
                             :size "大小"
                             :snapshot "快照"
                             :snapshots "快照"
                             :snapshot-comment "快照描述"
                             :source "来源"
                             :source-ip "来源地址"
                             :src "来源"
                             :state "状态"
                             :subnet "子网"
                             :tag- "标签："
                             :this-zone "这台虚机"
                             :timestamp "时间戳"
                             :type "类型"
                             :uploading "上传中"
                             :uuid "UUID"
                             :version "版本"
                             }
                  :field {
                          :name "名称"
                          }
                  :del {
                          :delete "删除"
                          :alert-msg "您确定要删除 "
                  }})
