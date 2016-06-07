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
