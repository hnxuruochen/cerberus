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
