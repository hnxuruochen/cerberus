(ns cerberus.multi-lang.en)

(def tconfig-map {
                  :missing "missing key"
                  :core {
                         :clients "Clients"
                         :configuration "Configuration"
                         :datasets "Datasets"
                         :groupings "Stack & Clusters"
                         :hypervisors "Hypervisors"
                         :ip-ranges "IP Ranges"
                         :logout "Logout"
                         :machines "Machines"
                         :networks "Networks"
                         :notifications "Notifications"
                         :orgs "Organisations"
                         :packages "Packages"
                         :roles "Roles"
                         :users "Users"
                         }
                  :clients {:clients "Clients"}
                  :clients-view {:auth-change-secret "Change Secret"
                                 :auth-new-secret "New Secret"
                                 :auth-confirm-secret "Confirm"
                                 :auth-change "Change"

                                 :uri-add-redirect-uri "Add Redirect URI"

                                 :sections-auth "Authentication"
                                 :sections-permissions "Permissions"
                                 :sections-uri "URI's"
                                 :sections-metadata "Metadata"}
                  :clients-api {:client-delete-succ "Client deleted."
                                :client-delete-fail "Failed to delete client."
                                :grant-succ "Permission granted."
                                :grant-fail "Failed to grant permission."
                                :revoke-succ "Permission revoked."
                                :revoke-fail "Failed to revoke permission."
                                :secret-succ "Secret changed."
                                :secret-fail "Failed to change secret."
                                :uri-add-succ "URI added."
                                :uri-add-fail "Failed to add uri."
                                :uri-del-succ "URI deleted."
                                :uri-del-fail "Failed to delete URI."}
                  :clients-create {:name "Name"
                                   :secret "Secret"}


                  :datasets {:datasets "Datasets"
                             :version "Version"
                             :imported "Imported"}
                  :datasets-api {:dataset-del-succ "Dataset deleted."
                                 :dataset-del-fail "Failed to delete dataset."
                                 :dataset-import-start "Dataset import started."
                                 :dataset-import-fail "Dataset import failed."
                                 :nic-add-succ "Dataset network added."
                                 :nic-add-fail "Failed to add dataset network."
                                 :nic-del-succ "Dataset network added."
                                 :nic-del-fail "Failed to add dataset network."}
                  :datasets-create {:name "Name"
                                    :version "Version"
                                    :type "Type"
                                    :published "Published"
                                    :size "Size"}
                  :datasets-view {:home-type "type: "
                                  :home-version "version: "
                                  :networks-desc "Description"
                                  :networks-add "Add"

                                  :sections-general "General"
                                  :sections-requirements "Requirements"
                                  :sections-networks "Networks"
                                  :sections-metadata "Metadata"}

                  :groupings {:groupings "Groupings"
                              :name "Name"
                              :type "Type"
                              :elements "Elements"}
                  :groupings-view {:config-config "Configuration"
                                   :config-value "Value"
                                   :config-set-config "Set Configuration"

                                   :general-general "General"
                                   :general-name "Name"
                                   :general-type "Type"

                                   :element-add "Add"


                                   :sections-general "General"
                                   :sections-elements "Elements"
                                   :sections-configuration "Configuration"
                                   :sections-metadata "Meatadata"}
                  :groupings-create {:type "Type"
                                     :cluster "Cluster"
                                     :stack "Stack"
                                     :name "Name"}
                  :groupings-api {:grouping-del-succ "Grouping deleted."
                                  :grouping-del-fail "Failed to delete grouping."
                                  :config-set-succ "Configuration updated."
                                  :config-set-fail "Failed to update configuration."
                                  :config-del-succ "Configuration deleted."
                                  :config-del-fail "Failed to delete configuration."
                                  :element-add-succ "Element added."
                                  :element-add-fail "Failed to add element."
                                  :element-del-succ "Element deleted."
                                  :element-del-fail "Failed to delete element."}

                  :hypervisors {:hypervisors "Hypervisors"
                                :name "Name"
                                :version "Version"
                                :os-ver "OS Version"
                                :host "Host"
                                :last-seen "Last Seen"
                                :uptime "Uptime"
                                :used-mem "Used Memory"
                                :reserved-mem "Reserved Memory"
                                :free-mem "Free Memory"}
                  :hypervisors-api {:hv-del-succ "Hypervisor removed."
                                    :hv-del-fail "Failed to remove hypervisor."
                                    :hv-rename-succ "Hypervisor renamed."
                                    :hv-rename-fail "Failed to rename hypervisor."
                                    :svc-change-succ "Service state changed."
                                    :svc-change-fail "Failed to change service state."
                                    :config-set-succ "Configuration updated."
                                    :config-set-fail "Failed to change configuration."
                                    :char-set-succ "Characteristic set."
                                    :char-set-fail "Failed to set characteristic."
                                    :char-del-succ "Characteristic deleted."
                                    :char-del-fail "Failed to delete characteristic."}
                  :hypervisors-view {:gen-info "Info"
                                     :gen-info-host "Host"
                                     :gen-info-os "Operating System"
                                     :gen-info-os-ver "OS Version"
                                     :gen-info-chunter-ver "Chunter Version"
                                     :gen-info-last-boot "Last Boot"

                                     :gen-hw-hardware "Hardware"
                                     :gen-hw-cpu "CPU"
                                     :gen-hw-cores "Cores"
                                     :gen-hw-mainboard "Mainboard"
                                     :gen-hw-manufacturer "Manufacturer"
                                     :gen-hw-sn "Serial Number"
                                     :gen-hw-vs "Virtualisation Support"

                                     :gen-mem-memory "Memory"
                                     :gen-mem-total "Total"
                                     :gen-mem-provisioned "Provisioned"
                                     :gen-mem-free "Free"
                                     :gen-mem-reserved "Reserved"
                                     :gen-mem-l1-size "L1 Cache Size"
                                     :gen-mem-l1-hit "L1 Cache Hit %"

                                     :gen-st-storage "Storage"
                                     :gen-st-disks "Disks"
                                     :gen-st-pools "Pools"
                                     :gen-st-health "Health: "
                                     :gen-st-size "Size: "
                                     :gen-st-free "Free: "
                                     :gen-st-used "Used: "

                                     :gen-home-unknown "Unknown"
                                     :gen-home-change-alias "Change Alias"
                                     :gen-home-networks "Networks"

                                     :char-char "Characteristic"
                                     :char-value "Value"
                                     :char-add-char "Add Characteristic"

                                     :metrics-cpu "CPU"

                                     :sections-general "General"
                                     :sections-services "Services"
                                     :sections-char "Characteristics"
                                     :sections-metrics "Metrics"
                                     :sections-metadata "Metadata"}

                  :ipranges {:ip-ranges "IP Ranges"}
                  :ipranges-api {:delete-succ "IP range deleted."
                                 :delete-fail "Failed to delete IP range."}
                  :ipranges-create {:name "Name"
                                    :nic-tag "NIC Tag"
                                    :vlan "VLAN"
                                    :subnet-ip "Subnet IP"
                                    :netmask "Netmask"
                                    :gateway "Gateway"
                                    :first "First"
                                    :last "Last"}
                  :ipranges-view {:gen-general-general "General"
                                  :gen-general-uuid "UUID"
                                  :gen-general-network "Network"
                                  :gen-general-gateway "Gateway"
                                  :gen-general-netmask "Netmask"
                                  :gen-general-vlan "VLAN"
                                  :gen-general-tag "Tag"

                                  :gen-ips-ips "IPs"
                                  :gen-ips-free "Free"
                                  :gen-ips-used "Used"

                                  :ips-free "Free"
                                  :ips-used "Used"

                                  :sections-general "General"
                                  :sections-ips "IPs"
                                  :sections-metadata "Metadata"}

                  :networks {:networks "Networks"
                             :ip-ranges "IP Ranges"}
                  :networks-api {:network-del-succ "Network deleted."
                                 :network-del-fail "Failed to delete network."
                                 :iprange-add-succ "IP range added."
                                 :iprange-add-fail "Failed to add IP range."
                                 :iprange-del-succ "IP range deleted."
                                 :iprange-del-fail "Failed to delete IP range."}
                  :networks-create {:name "Name"}
                  :networks-view {:iprange-add "Add"
                                  :gen-general "General"
                                  :gen-uuid "UUID"
                                  :gen-ipranges "IPRanges"
                                  :sections-general "General"
                                  :sections-ipranges "IP Ranges"
                                  :sections-metadata "Metadata"}


                  :orgs {:orgs "Organisations"}
                  :orgs-api {:orgs-del-succ "Organisation deleted."
                             :orgs-del-fail "Failed to delete organisation."
                             :trigger-del-succ "Trigger deleted."
                             :trigger-del-fail "Failed to delete trigger."
                             :trigger-add-succ "Trigger added."
                             :trigger-add-fail "Failed to add trigger."
                             :res-dec-succ "Resource decreased."
                             :res-dec-fail "Failed to decrease resource."
                             :res-inc-succ "Resource increased."
                             :res-inc-fail "Failed to increase resource."
                             :net-set-succ "Network set."
                             :net-set-fail "Failed to set network."
                             :res-del-succ "Resource deleted."
                             :res-del-fail "Failed to delete resource."}
                  :orgs-create {:name "Name"}
                  :orgs-view {:res-res "Resource"
                              :res-value "Value"
                              :res-inc "Increase"
                              :res-dec "Decrease"

                              :docker-set-pub-net "Set Public Network"
                              :docker-set-priv-net "Set Private Network"

                              :tr-trigger-vm "When a VM is created"
                              :tr-trigger-user "When a User is created"
                              :tr-trigger-dataset "When a Dataset is created"

                              :tr-rest-role "Grant the role "
                              :tr-rest-user "Grant the user "
                              :tr-rest-join-org "Join the Organisation "
                              :tr-rest-rcv-role "Receive the role "

                              :tr-when "When "
                              :tr-a-vm "a VM"
                              :tr-a-user "a User"
                              :tr-a-dataset "a Dataset"
                              :tr-is-created " is created "
                              :tr-join-them-org "join them to the organisation"
                              :tr-give-them-role "give them the role"
                              :tr-grant-role "grnat the role"
                              :tr-grant-user "grant the user"
                              :tr-permissions-to " permissions to "
                              :tr-the-new " the new "
                              :tr-vm "VM"
                              :tr-user "User"
                              :tr-dataset "Dataset"
                              :tr-period "."
                              :tr-create-trigger "Create Trigger"
                              :tr-event "Event"
                              :tr-rest "Rest"

                              :gen-general "General"
                              :gen-uuid "UUID"
                              :gen-res "Resources"
                              :gen-triggers "Triggers"
                              :gen-tr-total "Total"
                              :gen-tr-vm "VM Creation"
                              :gen-tr-user "User Creation"
                              :gen-tr-dataset "Dataset Creation"

                              :sections-general "General"
                              :sections-res "Resources"
                              :sections-accounting "Accounting"
                              :sections-triggers "Triggers"
                              :sections-docker "Docker"
                              :sections-metadata "Metadata"}

                  :packages {:action-clone "Clone"
                             :pkgs "Packages"
                             :ttl-cpu "CPU"
                             :ttl-quota "Quota"
                             :ttl-ram "RAM"}
                  :packages-api {:del-succ "Package deleted."
                                 :del-fail "Failed to delete package."}
                  :packages-create {:cond-must "Must"
                                    :cond-cant "Can't"
                                    :cond-scale "Scale"
                                    :cond-rand "Random"

                                    :lbl-name "Name"
                                    :lbl-cpu "CPU"
                                    :lbl-mem "Memory"
                                    :lbl-disk "Disk"
                                    :lbl-io-prio "IO Priority"
                                    :lbl-blk-size "Block Size"
                                    :lbl-compr "Compression"

                                    :rules-rules "Rules"
                                    :rules-attr "Attribute"
                                    :rules-val "Value"
                                    :rules-low "Low"
                                    :rules-high "High"
                                    :rules-add "Add"

                                    :org-res "Org Resources"
                                    :org-res-res "Resource"
                                    :org-res-req "Required"
                                    :org-res-add "Add"
                                    :org-res-val "Value"}
                  :packages-view {:gen-general "General"
                                  :gen-uuid "UUID"
                                  :gen-req "Requirements"
                                  :gen-cpu-mem "CPU / Memory"
                                  :gen-ram "Ram"
                                  :gen-cpu-cap "CPU Capacity"
                                  :gen-cpu-shares "CPU Shares"
                                  :gen-disk "Disk"
                                  :gen-disk-quota "Quota"
                                  :gen-disk-compr "Compression"
                                  :gen-disk-io-prio "IO Priority"
                                  :gen-disk-blk-size "Block Size"
                                  :gen-org-res "Org Resources"

                                  :req-scale "scale"
                                  :req-rand "random"
                                  :req-between " between "
                                  :req-and " and "

                                  :sections-general "General"
                                  :sections-req "Requirements"
                                  :sections-metadata "Metadata"}


                  :roles {:roles "Roles"}
                  :roles-view {:general "General"
                               :permissions "Permission"
                               :metadata "Metadata"}
                  :roles-api {:delete-succ "Role deleted."
                              :delete-fail "Failed to delete role."
                              :grant-succ "Permission granted."
                              :grant-fail "Failed to grant permission."
                              :revoke-succ "Permission revoked."
                              :revoke-fail "Failed to revoke permission."}
                  :roles-create {:name "Name"}
                  :vms {
                        :brand "Brand"
                        :cluster "Cluster"
                        :console "Console"
                        :cpu "CPU"
                        :created "Created"
                        :created-ago "Created ago"
                        :creator "Creator"
                        :dataset "Dataset"
                        :delete "Delete"
                        :failed "failed"
                        :hostname "Hostname"
                        :hypervisor "Hypervisor"
                        :ip "IP"
                        :lock "Lock"
                        :machines "Machines"
                        :memory "Memory"
                        :name "Name"
                        :owner "Owner"
                        :package "Package"
                        :reboot "Reboot"
                        :running "running"
                        :start "Start"
                        :state "State"
                        :stop "Stop"
                        :stopped "stopped"
                        :unlock "Unlock"
                        }})
