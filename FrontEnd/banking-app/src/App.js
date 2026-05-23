import React, { useEffect, useState } from "react";
import "./App.css";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend,
} from "chart.js";
import jsPDF from "jspdf";

ChartJS.register(BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const API = "http://localhost:8081/api";

function App() {
  const [screen, setScreen] = useState("landing");
  const [page, setPage] = useState("home");

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [accounts, setAccounts] = useState([]);
  const [transactions, setTransactions] = useState([]);

  const [name, setName] = useState("");
  const [accountSearch, setAccountSearch] = useState("");
  const [transactionSearch, setTransactionSearch] = useState("");
  const [filterDate, setFilterDate] = useState("");
  const [sortType, setSortType] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    loadAccounts();
    loadTransactions();
  }, []);

  const loadAccounts = async () => {
    const res = await fetch(`${API}/accounts`);
    const data = await res.json();
    setAccounts(data);
  };

  const loadTransactions = async () => {
    const res = await fetch(`${API}/transactions`);
    const data = await res.json();
    setTransactions(data);
  };

  const handleLogin = () => {
    if (username === "admin" && password === "1234") {
      setScreen("dashboard");
      setMessage("Login successful");
    } else {
      setMessage("Invalid credentials");
    }
  };

  const logout = () => {
    setScreen("landing");
    setUsername("");
    setPassword("");
    setMessage("");
  };

  const createAccount = async () => {
    if (name.trim().length < 3) {
      setMessage("Enter a valid customer name");
      return;
    }

    await fetch(`${API}/account?name=${encodeURIComponent(name)}`, {
      method: "POST",
    });

    setName("");
    setMessage("Account created successfully");
    loadAccounts();
  };

  const deposit = async (id) => {
    const amount = Number(prompt("Enter deposit amount"));

    if (!amount || amount <= 0) {
      setMessage("Enter a valid amount");
      return;
    }

    await fetch(`${API}/deposit?id=${id}&amount=${amount}`, {
      method: "POST",
    });

    setMessage("Deposit successful");
    loadAccounts();
    loadTransactions();
  };

  const withdraw = async (id) => {
    const amount = Number(prompt("Enter withdrawal amount"));

    if (!amount || amount <= 0) {
      setMessage("Enter a valid amount");
      return;
    }

    const res = await fetch(`${API}/withdraw?id=${id}&amount=${amount}`, {
      method: "POST",
    });

    if (!res.ok) {
      setMessage("Insufficient balance");
      return;
    }

    setMessage("Withdrawal successful");
    loadAccounts();
    loadTransactions();
  };

  const deleteAccount = async (id) => {
    await fetch(`${API}/account/${id}`, {
      method: "DELETE",
    });

    setMessage("Account deleted");
    loadAccounts();
    loadTransactions();
  };

  const formatDate = (dateValue) => {
    const date = new Date(dateValue);
    return isNaN(date.getTime()) ? "N/A" : date.toLocaleString();
  };

  const totalBalance = accounts.reduce((sum, acc) => sum + acc.balance, 0);

  const totalDeposits = transactions
    .filter((t) => t.type === "Deposit")
    .reduce((sum, t) => sum + t.amount, 0);

  const totalWithdrawals = transactions
    .filter((t) => t.type === "Withdraw")
    .reduce((sum, t) => sum + t.amount, 0);

  const filteredAccounts = accounts.filter(
    (acc) =>
      acc.name.toLowerCase().includes(accountSearch.toLowerCase()) ||
      String(acc.accountNumber).includes(accountSearch)
  );

  const filteredTransactions = transactions
    .filter(
      (t) =>
        t.name.toLowerCase().includes(transactionSearch.toLowerCase()) ||
        t.type.toLowerCase().includes(transactionSearch.toLowerCase()) ||
        String(t.accountNumber).includes(transactionSearch)
    )
    .filter((t) => {
      if (!filterDate) return true;
      return String(t.date).slice(0, 10) === filterDate;
    })
    .sort((a, b) => {
      if (sortType === "amountAsc") return a.amount - b.amount;
      if (sortType === "amountDesc") return b.amount - a.amount;
      if (sortType === "dateAsc") return new Date(a.date) - new Date(b.date);
      if (sortType === "dateDesc") return new Date(b.date) - new Date(a.date);
      return 0;
    });

  const chartData = {
    labels: ["Deposits", "Withdrawals"],
    datasets: [
      {
        label: "Amount",
        data: [totalDeposits, totalWithdrawals],
        backgroundColor: ["#0b7a3b", "#d71920"],
      },
    ],
  };

  const downloadPDF = () => {
    const doc = new jsPDF();
    doc.setFontSize(18);
    doc.text("MyBank Transaction Statement", 20, 20);

    let y = 40;

    filteredTransactions.forEach((t, index) => {
      doc.setFontSize(10);
      doc.text(
        `${index + 1}. ${t.name} | Acc: ${t.accountNumber} | ${t.type} | Rs.${t.amount} | ${formatDate(t.date)}`,
        20,
        y
      );

      y += 10;

      if (y > 280) {
        doc.addPage();
        y = 20;
      }
    });

    doc.save("bank-statement.pdf");
  };

  if (screen === "landing") {
    return (
      <div className="landingPage">
        <nav className="landingNav">
          <h2>🏦 MyBank</h2>
          <div>
            <a href="#services">Services</a>
            <a href="#about">About</a>
            <a href="#contact">Contact</a>
            <button onClick={() => setScreen("login")}>Login</button>
          </div>
        </nav>

        <section className="landingHero">
          <div className="heroOverlay">
            <h1>Smart Digital Banking</h1>
            <p>
              Secure account management, transaction tracking, analytics and PDF
              statements.
            </p>
            <button onClick={() => setScreen("login")}>
              Continue to Net Banking
            </button>
          </div>
        </section>

        <section id="services" className="section">
          <h2>Our Services</h2>
          <div className="serviceGrid">
            <div className="serviceCard">💰 Savings Account</div>
            <div className="serviceCard">💳 Cards & Payments</div>
            <div className="serviceCard">📊 Analytics Dashboard</div>
            <div className="serviceCard">📄 PDF Statements</div>
          </div>
        </section>

        <section id="about" className="section about">
          <h2>About MyBank</h2>
          <p>
            MyBank is a full-stack banking management system built using React,
            Spring Boot, and MySQL.
          </p>
        </section>

        <section id="contact" className="section">
          <h2>Contact Us</h2>
          <p>Email: support@mybank.com</p>
          <p>Phone: +91 98765 43210</p>
          <p>Location: Bangalore, India</p>
        </section>

        <footer className="footer">© 2026 MyBank. All rights reserved.</footer>
      </div>
    );
  }

  if (screen === "login") {
    return (
      <div className="loginPage">
        <div className="loginBox">
          <h1>🏦 MyBank Login</h1>
          <p>Secure Digital Banking Access</p>

          {message && <div className="message">{message}</div>}

          <input
            className="input"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            className="input"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button className="primaryBtn" onClick={handleLogin}>
            Login
          </button>

          <p className="demoText">Demo: admin / 1234</p>
        </div>
      </div>
    );
  }

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <h2>🏦 MyBank</h2>
        <button onClick={() => setPage("home")}>🏠 Home</button>
        <button onClick={() => setPage("accounts")}>👤 Accounts</button>
        <button onClick={() => setPage("transactions")}>
          📄 Transactions
        </button>
        <button className="logoutBtn" onClick={logout}>
          Logout
        </button>
      </aside>

      <main className="mainContent">
        {message && <div className="message">{message}</div>}

        {page === "home" && (
          <>
            <div className="pageBanner homeBanner">
              <h1>Net Banking Dashboard</h1>
              <p>
                Monitor balances, deposits, withdrawals and overall banking
                activity.
              </p>
            </div>

            <div className="stats">
              <div className="statCard">
                <h3>Total Balance</h3>
                <p>₹{totalBalance}</p>
              </div>

              <div className="statCard">
                <h3>Total Deposits</h3>
                <p>₹{totalDeposits}</p>
              </div>

              <div className="statCard">
                <h3>Total Withdrawals</h3>
                <p>₹{totalWithdrawals}</p>
              </div>
            </div>

            <div className="chartBox">
              <h2>Transaction Analytics</h2>
              <Bar data={chartData} />
            </div>
          </>
        )}

        {page === "accounts" && (
          <>
            <div className="pageBanner accountsBanner">
              <h1>Account Management</h1>
              <p>Create, search and manage customer bank accounts.</p>
            </div>

            <div className="panel">
              <input
                className="input"
                placeholder="Enter customer name"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />

              <button className="primaryBtn" onClick={createAccount}>
                Create Account
              </button>

              <input
                className="input"
                placeholder="Search account by name or number"
                value={accountSearch}
                onChange={(e) => setAccountSearch(e.target.value)}
              />

              <div className="cardGrid">
                {filteredAccounts.map((acc) => (
                  <div className="accountCard" key={acc.id}>
                    <h3>{acc.name}</h3>
                    <p>Account No: {acc.accountNumber}</p>
                    <h2>₹{acc.balance}</h2>

                    <button
                      className="depositBtn"
                      onClick={() => deposit(acc.id)}
                    >
                      Deposit
                    </button>

                    <button
                      className="withdrawBtn"
                      onClick={() => withdraw(acc.id)}
                    >
                      Withdraw
                    </button>

                    <button
                      className="deleteBtn"
                      onClick={() => deleteAccount(acc.id)}
                    >
                      Delete
                    </button>
                  </div>
                ))}

                {filteredAccounts.length === 0 && <p>No accounts found</p>}
              </div>
            </div>
          </>
        )}

        {page === "transactions" && (
          <>
            <div className="pageBanner transactionsBanner">
              <h1>Transaction History</h1>
              <p>Search, filter, sort and download bank statements.</p>
            </div>

            <div className="panel">
              <div className="controls">
                <input
                  className="input"
                  placeholder="Search transactions"
                  value={transactionSearch}
                  onChange={(e) => setTransactionSearch(e.target.value)}
                />

                <input
                  className="input"
                  type="date"
                  value={filterDate}
                  onChange={(e) => setFilterDate(e.target.value)}
                />

                <select
                  className="input"
                  value={sortType}
                  onChange={(e) => setSortType(e.target.value)}
                >
                  <option value="">Sort By</option>
                  <option value="amountAsc">Amount Low to High</option>
                  <option value="amountDesc">Amount High to Low</option>
                  <option value="dateAsc">Oldest First</option>
                  <option value="dateDesc">Latest First</option>
                </select>

                <button className="primaryBtn" onClick={downloadPDF}>
                  Download PDF
                </button>
              </div>

              <table className="table">
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Account No</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date & Time</th>
                  </tr>
                </thead>

                <tbody>
                  {filteredTransactions.map((t) => (
                    <tr key={t.id}>
                      <td>{t.name}</td>
                      <td>{t.accountNumber}</td>
                      <td
                        className={
                          t.type === "Deposit" ? "greenText" : "redText"
                        }
                      >
                        {t.type}
                      </td>
                      <td>₹{t.amount}</td>
                      <td>{formatDate(t.date)}</td>
                    </tr>
                  ))}

                  {filteredTransactions.length === 0 && (
                    <tr>
                      <td colSpan="5">No transactions found</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </>
        )}
      </main>
    </div>
  );
}

export default App;