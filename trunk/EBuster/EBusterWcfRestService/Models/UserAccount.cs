using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;

namespace EBusterWcfRestService.Models
{
    [DataContract]
    public class UserAccountModel
    {
        [DataMember]
        public string Email;
        [DataMember]
        public string Password;
    }

    public class UserAccount
    {
        public UserAccount(string Email, string Password)
        {
            this.Email = Email;
            this.Password = Password;
        }

        /**
         * MH: Needed by EntityFramework 
         **/
        public UserAccount()
        {
        }

        [Key]
        public string Email { get; set; }
        public string Password { get; set; }
    }

    public class UserAccountDBContext : DbContext
    {
        public DbSet<UserAccount> UserAccounts { get; set; }
    }
}