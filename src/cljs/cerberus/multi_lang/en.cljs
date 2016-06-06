(ns cerberus.multi-lang.en)

(def tconfig-map {
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
                  :roles-create {:name "Name"}})
