using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace EBusterWcfRestService.Models
{
    public class Account
    {

        [Required]
        [Key]
        [DataType(DataType.EmailAddress)]
        [Display(Name = "Email address")]
        public string Email { get; set; }

        [Required]
        [StringLength(100, ErrorMessage = "The {0} must be at least {2} characters long.", MinimumLength = 6)]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        public Account(string Email, string Password){
            this.Email = Email;
            this.Password = Password;
        }
    }

    public class AccountDBContext : DbContext
    {
        public DbSet<Account> Accounts { get; set; }
    }

}