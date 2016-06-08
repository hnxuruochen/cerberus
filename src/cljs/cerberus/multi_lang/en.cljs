(ns cerberus.multi-lang.en)

(def tconfig-map {
                  :missing "missing key"
                  :add {
                        :new-machine "New Machine"
                        :new-users "New User"
                        :new-roles "New Role"
                        :new-orgs "New Organisation"
                        :new-clients "New Client"
                        :new-groupings "New Grouping"
                        :new-packages "New Package"
                        :new-networks "New Network"
                        :new-ipranges "New IP-Range"
                        :new-dtrace "New DTrace Script"
                        :new-datasets "Import Dataset"
                        :btn-machine "Create"
                        :btn-users "Add User"
                        :btn-roles "Add Role"
                        :btn-orgs "Add Organisation"
                        :btn-clients "Add Client"
                        :btn-groupings "Add Grouping"
                        :btn-packages "Add Package"
                        :btn-networks "Add Network"
                        :btn-ipranges "Add IP-Range"
                        :btn-dtrace "Add DTrace Script"
                        :btn-datasets "Import"
                        }
                  :core {
                         :clients "Clients"
                         :configuration "Configuration"
                         :datasets "Datasets"
                         :groupings "Stack & Clusters"
                         :hypervisors "Hypervisors"
                         :ip-ranges "IP Ranges"
                         :login "Login"
                         :logout "Logout"
                         :machines "Machines"
                         :networks "Networks"
                         :notifications "Notifications"
                         :orgs "Organisations"
                         :packages "Packages"
                         :password "Password"
                         :roles "Roles"
                         :users "Users"
                         :yubikey "YubiKey"
                         }
                  :clients {
                            :clients "Clients"
                            }
                  :clients-view {
                                 :auth-change-secret "Change Secret"
                                 :auth-new-secret "New Secret"
                                 :auth-confirm-secret "Confirm"
                                 :auth-change "Change"
                                 :uri-add-redirect-uri "Add Redirect URI"
                                 :sections-auth "Authentication"
                                 :sections-permissions "Permissions"
                                 :sections-uri "URI's"
                                 :sections-metadata "Metadata"
                                 }
                  :clients-api {
                                :client-delete-succ "Client deleted."
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
                                :uri-del-fail "Failed to delete URI."
                                }
                  :clients-create {
                                   :name "Name"
                                   :secret "Secret"
                                   }
                  :datasets {
                             :datasets "Datasets"
                             :version "Version"
                             :imported "Imported"
                             }
                  :datasets-api {
                                 :dataset-del-succ "Dataset deleted."
                                 :dataset-del-fail "Failed to delete dataset."
                                 :dataset-import-start "Dataset import started."
                                 :dataset-import-fail "Dataset import failed."
                                 :nic-add-succ "Dataset network added."
                                 :nic-add-fail "Failed to add dataset network."
                                 :nic-del-succ "Dataset network added."
                                 :nic-del-fail "Failed to add dataset network."
                                 }
                  :datasets-create {
                                    :name "Name"
                                    :version "Version"
                                    :type "Type"
                                    :published "Published"
                                    :size "Size"
                                    }
                  :datasets-view {
                                  :home-type "type: "
                                  :home-version "version: "
                                  :networks-desc "Description"
                                  :networks-add "Add"
                                  :sections-general "General"
                                  :sections-requirements "Requirements"
                                  :sections-networks "Networks"
                                  :sections-metadata "Metadata"
                                  }
                  :groupings {
                              :groupings "Groupings"
                              :name "Name"
                              :type "Type"
                              :elements "Elements"
                              }
                  :groupings-view {
                                   :config-config "Configuration"
                                   :config-value "Value"
                                   :config-set-config "Set Configuration"
                                   :general-general "General"
                                   :general-name "Name"
                                   :general-type "Type"
                                   :element-add "Add"
                                   :sections-general "General"
                                   :sections-elements "Elements"
                                   :sections-configuration "Configuration"
                                   :sections-metadata "Meatadata"
                                   }
                  :groupings-create {
                                     :type "Type"
                                     :cluster "Cluster"
                                     :stack "Stack"
                                     :name "Name"
                                     }
                  :groupings-api {
                                  :grouping-del-succ "Grouping deleted."
                                  :grouping-del-fail "Failed to delete grouping."
                                  :config-set-succ "Configuration updated."
                                  :config-set-fail "Failed to update configuration."
                                  :config-del-succ "Configuration deleted."
                                  :config-del-fail "Failed to delete configuration."
                                  :element-add-succ "Element added."
                                  :element-add-fail "Failed to add element."
                                  :element-del-succ "Element deleted."
                                  :element-del-fail "Failed to delete element."
                                  }
                  :hypervisors {
                                :hypervisors "Hypervisors"
                                :name "Name"
                                :version "Version"
                                :os-ver "OS Version"
                                :host "Host"
                                :last-seen "Last Seen"
                                :uptime "Uptime"
                                :used-mem "Used Memory"
                                :reserved-mem "Reserved Memory"
                                :free-mem "Free Memory"
                                }
                  :hypervisors-api {
                                    :hv-del-succ "Hypervisor removed."
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
                                    :char-del-fail "Failed to delete characteristic."
                                    }
                  :hypervisors-view {
                                     :gen-info "Info"
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
                                     :sections-metadata "Metadata"
                                     }
                  :metrics {
                            :error-info "No metric storage seems to be configured please install DalmatinerDB and Tachyon to use this feature"
                            }
                  :ipranges {
                             :ip-ranges "IP Ranges"
                             }
                  :ipranges-api {
                                 :delete-succ "IP range deleted."
                                 :delete-fail "Failed to delete IP range."
                                 }
                  :ipranges-create {
                                    :name "Name"
                                    :nic-tag "NIC Tag"
                                    :vlan "VLAN"
                                    :subnet-ip "Subnet IP"
                                    :netmask "Netmask"
                                    :gateway "Gateway"
                                    :first "First"
                                    :last "Last"
                                    }
                  :ipranges-view {
                                  :gen-general-general "General"
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
                                  :sections-metadata "Metadata"
                                  }
                  :networks {
                             :networks "Networks"
                             :ip-ranges "IP Ranges"
                             }
                  :networks-api {
                                 :network-del-succ "Network deleted."
                                 :network-del-fail "Failed to delete network."
                                 :iprange-add-succ "IP range added."
                                 :iprange-add-fail "Failed to add IP range."
                                 :iprange-del-succ "IP range deleted."
                                 :iprange-del-fail "Failed to delete IP range."
                                 }
                  :networks-create {
                                    :name "Name"
                                    }
                  :networks-view {
                                  :iprange-add "Add"
                                  :gen-general "General"
                                  :gen-uuid "UUID"
                                  :gen-ipranges "IPRanges"
                                  :sections-general "General"
                                  :sections-ipranges "IP Ranges"
                                  :sections-metadata "Metadata"
                                  }
                  :orgs {
                         :orgs "Organisations"
                         }
                  :orgs-api {
                             :orgs-del-succ "Organisation deleted."
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
                             :res-del-fail "Failed to delete resource."
                             }
                  :orgs-create {
                                :name "Name"
                                }
                  :orgs-view {
                              :res-res "Resource"
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
                               :metadata "Metadata"
                               }
                  :roles-api {
                              :delete-succ "Role deleted."
                              :delete-fail "Failed to delete role."
                              :grant-succ "Permission granted."
                              :grant-fail "Failed to grant permission."
                              :revoke-succ "Permission revoked."
                              :revoke-fail "Failed to revoke permission."
                              }
                  :roles-create {
                                 :name "Name"
                                 }
                  :services {
                             :clear "Clear"
                             :disable "Disable"
                             :disabled "disabled"
                             :enable "Enable"
                             :legacy-run "legacy_run"
                             :offline "offline"
                             :online "online"
                             :refresh "Refresh"
                             :restart "Restart"
                             :service "Service"
                             :state "State"
                             }
                  :users-view {
                               :change-password "Change password"
                               :new-password "New Password"
                               :confirm "Confirm"
                               :change "Change"
                               :password-changed "Password changed"
                               :new-ssh-key "New SSH Public Key"
                               :ssh-key "Key"
                               :ssh-name "Name"
                               :ssh-add "Add"
                               :register-yubikey "Register YubiKey"
                               :yubi-add "Add"
                               :yubi-key "Key"
                               :ssh-keys "SSH Keys"
                               :yubi-keys "Yubi Keys"
                               :roles-add "Add"
                               :orgs-add "Add"
                               :token-api-key "API Key"
                               :token-api-key-exist-1 "Your API key as been created, this is the only time you will be able to access it, "
                               :token-api-key-exist-2 "be sure to put it in the applicaiton you generated it for."
                               :token-api-key-exist-3 "Once this window is closed you will not be able to retrive it any again!"
                               :api-token "API Token"
                               :api-key-name "API Key Name"
                               :create-api-key "Create API Key"
                               :tokens-list-title-client "Client"
                               :tokens-list-title-type "Type"
                               :tokens-list-title-expiry "Expiry"
                               :tokens-list-title-revoke "Revoke"
                               :title-authentication "Authentication"
                               :title-permission "Permissions"
                               :title-roles "Roles"
                               :title-orgs "Orgs"
                               :title-tokens "Tokens"
                               :title-metadata "Metadata"
                               :users "Users"
                               :api "API"
                               :name "Name"
                               :organisation "Organisation"
                               }
                  :users-api {
                              :delete-user-succeed "User deletion successful."
                              :delete-user-failed "Failed to delete User."
                              :change-password-succeed "Password changed."
                              :change-password-failed "Failed to change password."
                              :permission-granted "Permission granted."
                              :grant-permission-failed "Failed to grant permission."
                              :revoke-permission-succeed "Permission revoked."
                              :revoke-permission-failed "Failed to revoke permission."
                              :revoke-token-succeed "Token revoked."
                              :revoke-token-failed "Failed to revoke token."
                              :add-ssh-key-succeed "SSH key added."
                              :add-ssh-key-failed "Failed to add SSH key."
                              :add-yubi-key-succeed "Yubikey added."
                              :add-yubi-key-failed "Failed to add Yubikey."
                              :removed-ssh-key-succeed "SSH key removed."
                              :removed-ssh-key-failed "Failed to remove SSH key."
                              :removed-yubi-key-succeed "Yubikey removed."
                              :removed-yubi-key-failed "Failed to remove Yubikey."
                              :add-role-succeed "Role added."
                              :add-role-failed "Failed to add role."
                              :remove-role-succeed "Role removed."
                              :remove-role-failed "Failed to remve role."
                              :organisation-joined "Organisation joined."
                              :organisation-join-failed "Failed to join organisation."
                              :active-organisation-succeed "Organisation set as active."
                              :active-organisation-failed "Failed to set organisation as active."
                              :remove-org-succeed "Organisation left."
                              :remove-org-failed "Failed to leave organisation."
                              }
                  :users-create {
                                 :name "Name"
                                 :password "Password"
                                 }
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
                        :installing-dataset "installing-dataset"
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
                        }
                  :vms-api {
                            :backup-create "Creating backup."
                            :backup-create-fail "Failed to create backup."
                            :backup-delete "Deleting backup."
                            :backup-delete-fail "Failed to delete backup."
                            :backup-restore "Restoring backup."
                            :backup-restore-fail "Failed to restore backup."
                            :firewall-add "Adding firewall rule."
                            :firewall-add-fail "Failed to add firewall rule."
                            :firewall-delete "Deleting firewall rule."
                            :firewall-delete-fail "Failed to delete firewall rule."
                            :hostname-set "Hostname set."
                            :hostname-set-fail "Failed to set hostname."
                            :network-add "Adding network."
                            :network-add-fail "Failed to add network."
                            :network-delete "Deleting network."
                            :network-delete-fail "Failed to add network."
                            :network-marking "Marking network as primary."
                            :network-marking-fail "Failed to mark network as primary."
                            :owner-change "Owner changed."
                            :owner-change-fail "Failed to change owner."
                            :service-change "Changing service state."
                            :service-change-fail "Failed to change service state."
                            :snapshot-create "Creating Snapshot."
                            :snapshot-create-fail "Failed to create snapshot."
                            :snapshot-delete "Deleting Snapshot."
                            :snapshot-delete-fail "Failed to delete snapshot."
                            :snapshot-restore "Restoring Snapshot."
                            :snapshot-restore-fail "Failed to restore snapshot."
                            :vm-conf-change "Changing VM configuration."
                            :vm-conf-change-fail "Failed to change the VM configuration."
                            :vm-del-fail "Failed to delete VM."
                            :vm-del-succ "VM Deletion successful."
                            :vm-reboot "Rebooting VM."
                            :vm-reboot-fail "Failed to reboot VM."
                            :vm-rm-fail "Failed to remove VM from hypervisor."
                            :vm-rm-succ "VM successfuly removed from hypervisor."
                            :vm-pkg-change "Changing VM package."
                            :vm-pkg-change-fail "Failed to change VM package, you are probably out of space."
                            :vm-start "Starting VM."
                            :vm-start-fail "Failed to start VM."
                            :vm-stop "Stopping VM."
                            :vm-stop-fail "Failed to stop VM."
                            }
                  :vms-create {
                               :advanced "Advanced"
                               :alias "Alias"
                               :cpu "CPU"
                               :dataset "Dataset"
                               :general "General"
                               :hostname "Hostname"
                               :key "Key"
                               :metadata "Metadata"
                               :name "Name"
                               :network "Network"
                               :networking "Networking"
                               :package "Package"
                               :quota "Quota"
                               :ran "RAM"
                               :set-resolvers "Set Resolvers"
                               :value "Value"
                               :version "Version"
                               :add "Add"
                               }
                  :vms-view {
                             :action "Action"
                             :add "Add"
                             :add-rule "add rule"
                             :alias "Alias"
                             :all "all"
                             :allow "allow"
                             :all-ports "All Ports"
                             :autoboot "Autoboot"
                             :backups "Backups"
                             :backup-comment "Backup Comment"
                             :backup-delete "Delete from hypervisor"
                             :backup-info "Once a backup is made it is possible to remove a zone from a hypervisor without deleting the backups, that way the zone can later on be deployed again."
                             :block "block"
                             :change "Change"
                             :change-alias "change-alias"
                             :comment "Comment"
                             :completed "completed"
                             :cpu "CPU"
                             :cpu- "CPU: "
                             :cpu-cap "CPU Cap"
                             :cpu-memory "CPU / Memory"
                             :cpu-shares "CPU Shares"
                             :create "CREATE"
                             :created "Created"
                             :create-image "CREATE IMAGE"
                             :custom "custom"
                             :dataset "Dataset"
                             :date "Date"
                             :delete "Delete"
                             :deploy-info "This vm is in 'limbo' it currently has no hypervisors assigned that means it can be re-deployed."
                             :description "Description"
                             :destination "Destination"
                             :dest-ip "Dest IP"
                             :direction "Direction"
                             :disk "Disk"
                             :dns-domain "DNS Domain"
                             :dst "dst"
                             :entry "Entry"
                             :failed "failed"
                             :firewall "Firewall"
                             :firewall-rules "Firewall Rules"
                             :gateway- "Gateway: "
                             :general "General"
                             :hostname "Hostname"
                             :hostname- "Hostname: "
                             :hypervisor "Hypervisor"
                             :image-info-0 "To create a new image follow this steps:"
                             :image-info-1 "Make sure everything is working fine on the vm"
                             :image-info-2 "Execute sm-prepare-image to make the vm image-ready"
                             :image-info-3 "Create a snapshot of the vm"
                             :image-info-4 "Set the data of the image, filling the form"
                             :image-info-5 "Choose the snapshot you want to base the image on"
                             :image-info-6 "Then, wait until the image is ready, the datasets page will reflect that state. After that, a new vm could be created with the new image. More info "
                             :image-info-7 "here"
                             :image-info-8 "."
                             :imaging "Imaging"
                             :inbound "Inbound"
                             :inbound-rules "Inbound rules"
                             :incremental "Incremental"
                             :io-priority "I/O Priority"
                             :ip "IP"
                             :ips "IPs"
                             :ip- "IP: "
                             :ip-range- "IP Range: "
                             :logs "Logs"
                             :mac- "MAC: "
                             :mask "Mask"
                             :max-swap "Max Swap"
                             :memory "Memory"
                             :memory- "Memory: "
                             :metadata "Metadata"
                             :metrics "Metrics"
                             :name "Name"
                             :netmask- "Netmask: "
                             :networking "Networking"
                             :networks "Networks"
                             :network- "Network: "
                             :new-backup "New Backup"
                             :new-snapshot "New Snapshot"
                             :os "OS"
                             :outbound "Outbound"
                             :outbpund-rules "Outbound rules"
                             :owner "Owner"
                             :pacakge "Package"
                             :ports "Ports"
                             :protocol "Protocol"
                             :quota "Quota"
                             :quota- "Quota: "
                             :resolvers "Resolvers"
                             :restore "Restore"
                             :roll-back "Roll Back"
                             :services "Services"
                             :set-owner "Set owner"
                             :size "Size"
                             :snapshot "Snapshot"
                             :snapshots "Snapshots"
                             :snapshot-comment "Snapshot Comment"
                             :source "Source"
                             :source-ip "Source IP"
                             :src "src"
                             :state "State"
                             :subnet "Subnet"
                             :tag- "Tag: "
                             :this-zone "this zone"
                             :timestamp "Timestamp"
                             :type "Type"
                             :uploading "uploading"
                             :uuid "UUID"
                             :version "Version"
                             }
                  :field {
                          :name "Name"
                          }
                  :del {
                        :delete "Delete"
                        :alert-msg "Are you sure that you want to delete "
                 }})
