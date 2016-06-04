(ns cerberus.multi-lang.en)

(def tconfig-map {
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
